package com.example.veterinario.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.veterinario.Model.Especialidad;
import com.example.veterinario.Repository.EspecialidadRepository;

@Service
public class EspecialidadService {
    @Autowired
    private EspecialidadRepository especialidadRepository;

    public String almacenarEspecialidad(Especialidad especialidad){
        if(especialidadRepository.findByNombre(especialidad.getNombre()) == null) {
            especialidadRepository.save(especialidad);
            return "Especialidad: " + especialidad.getNombre() + " Almacenado Correctamente!";
        }else{
            return "Especialidad:  " + especialidad.getNombre() + " Ya se encuentra Almacenado!";
        }
    }

    public List<Especialidad> listarEspecialidad(){
        return especialidadRepository.findAll();
    }

}
