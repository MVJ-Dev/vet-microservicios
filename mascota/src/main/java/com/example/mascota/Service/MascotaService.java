package com.example.mascota.Service;

import com.example.mascota.Repository.InformacionRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mascota.Model.Informacion;
import com.example.mascota.Model.Mascota;
import com.example.mascota.Repository.MascotaRepository;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private InformacionRepository informacionRepository;

    public String almacenarMascota(Mascota mascota){
        if(mascotaRepository.findByNombre(mascota.getNombre()) == null) {
            mascotaRepository.save(mascota);
            return "Mascota " + mascota.getNombre() + " Almacenado Correctamente!";
        }else{
            return "Mascota " + mascota.getNombre() + " Ya se encuentra Almacenado!";
        }
    }

    public List<Mascota> listarMascota(){
        return this.mascotaRepository.findAll();
    }

    public void actualizarMascota(Integer id, Mascota mascota){
        mascotaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
        mascota.setIdMascota(id);
        mascotaRepository.save(mascota);
    }

    public void eliminarMascota(Integer id){
        this.mascotaRepository.deleteById(id);
    }

    public Mascota obtenerMascota(Integer id){
        return mascotaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
    }

    
    public boolean validarMascota(Integer id) {
        return mascotaRepository.existsById(id);
    }

    public String asignarInfo(int idMascota, int idInfo){
        if(!mascotaRepository.existsById(idMascota)){
            return "La Mascota ingresada no existe";
        }else if(!informacionRepository.existsById(idInfo)){
            return "La informacion ingresada no existe";
        }else{
            Mascota mascota = mascotaRepository.findById(idMascota).get();
            Informacion informacion = informacionRepository.findById(idInfo).get();

            mascota.setInformacion(informacion);
            mascotaRepository.save(mascota);
            return "Informacion asignada correctamente";
        }
    }
}