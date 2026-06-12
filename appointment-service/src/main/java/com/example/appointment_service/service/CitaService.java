package com.example.appointment_service.service;

import com.example.appointment_service.model.Cita;
import com.example.appointment_service.model.HistorialEstado;
import com.example.appointment_service.repository.CitaRepository;
import com.example.appointment_service.repository.HistorialEstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private HistorialEstadoRepository historialRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Cita agendarCita(Cita cita) {
        
        String urlMascota = "http://localhost:8082/mascotas/validar/" + cita.getPetId();
        Boolean mascotaOk = restTemplate.getForObject(urlMascota, Boolean.class);

        
        String urlVet = "http://localhost:8083/veterinarios/validar/" + cita.getVetId();
        Boolean vetOk = restTemplate.getForObject(urlVet, Boolean.class);

        if (mascotaOk == null || !mascotaOk)
            throw new RuntimeException("La mascota no existe: " + cita.getPetId());
        if (vetOk == null || !vetOk)
            throw new RuntimeException("El veterinario no existe: " + cita.getVetId());

        return citaRepository.save(cita);
    }

    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }

    public Cita obtenerCita(Integer id) {
        return citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada: " + id));
    }

    
    public boolean validarCita(Integer id) {
        return citaRepository.existsById(id);
    }

    public List<Cita> citasPorMascota(Integer petId) {
        return citaRepository.findByPetId(petId);
    }

    public Cita cambiarEstado(Integer id, String nuevoEstado, String observacion) {
        Cita cita = obtenerCita(id);

        HistorialEstado historial = new HistorialEstado();
        historial.setCitaId(id);
        historial.setEstadoAnterior(cita.getEstado());
        historial.setEstadoNuevo(nuevoEstado);
        historial.setObservacion(observacion);
        historialRepository.save(historial);

        cita.setEstado(nuevoEstado);
        return citaRepository.save(cita);
    }

    public List<HistorialEstado> historialCita(Integer citaId) {
        return historialRepository.findByCitaId(citaId);
    }
}