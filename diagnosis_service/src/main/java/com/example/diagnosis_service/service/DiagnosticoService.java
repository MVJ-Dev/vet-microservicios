package com.example.diagnosis_service.service;

import com.example.diagnosis_service.model.Diagnostico;
import com.example.diagnosis_service.model.ArchivoAdjunto;
import com.example.diagnosis_service.repository.DiagnosticoRepository;
import com.example.diagnosis_service.repository.ArchivoAdjuntoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class DiagnosticoService {

    @Autowired
    private DiagnosticoRepository diagnosticoRepository;

    @Autowired
    private ArchivoAdjuntoRepository archivoRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Diagnostico registrarDiagnostico(Diagnostico d) {
        // Valida cita en appointment-service (CitaController SÍ usa /api/citas)
        String url = "http://localhost:8084/api/citas/validar/" + d.getCitaId();
        Boolean citaOk = restTemplate.getForObject(url, Boolean.class);

        if (citaOk == null || !citaOk)
            throw new RuntimeException("La cita no existe: " + d.getCitaId());

        return diagnosticoRepository.save(d);
    }

    public List<Diagnostico> listarDiagnosticos() {
        return diagnosticoRepository.findAll();
    }

    public Diagnostico obtenerDiagnostico(Integer id) {
        return diagnosticoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diagnóstico no encontrado: " + id));
    }

    public boolean validarDiagnostico(Integer id) {
        return diagnosticoRepository.existsById(id);
    }

    public List<Diagnostico> diagnosticosPorMascota(Integer petId) {
        return diagnosticoRepository.findByPetId(petId);
    }

    public List<Diagnostico> diagnosticosPorCita(Integer citaId) {
        return diagnosticoRepository.findByCitaId(citaId);
    }

    public ArchivoAdjunto agregarArchivo(ArchivoAdjunto archivo) {
        return archivoRepository.save(archivo);
    }

    public List<ArchivoAdjunto> archivosPorDiagnostico(Integer diagId) {
        return archivoRepository.findByDiagId(diagId);
    }
}