package com.example.veterinario.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.veterinario.Model.Especialidad;
import com.example.veterinario.Model.Veterinario;
import com.example.veterinario.Repository.EspecialidadRepository;
import com.example.veterinario.Repository.VeterinarioRepository;

@Service
public class VeterinarioService {

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    public String almacenarVeterinario(Veterinario veterinario){
        if(veterinarioRepository.findByNombre(veterinario.getNombre()) == null) {
            veterinarioRepository.save(veterinario);
            return "Veterinario " + veterinario.getNombre() + " Almacenado Correctamente!";
        }else{
            return "Veterinario " + veterinario.getNombre() + " Ya se encuentra Almacenado!";
        }
    }

    public List<Veterinario> listarVeterianrio(){
        return this.veterinarioRepository.findAll();
    }

    public void actualizarVeterinario(Integer id, Veterinario veterinario){
        veterinarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Veterinario no encontrado"));
        veterinarioRepository.save(veterinario);
    }

    public void eliminarVeterinario(Integer id){
        this.veterinarioRepository.deleteById(id);
    }

    public Veterinario obteneVeterinario(Integer id){
        return veterinarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Veterinario No encontrado"));
    }

    
    public boolean validarVeterinario(Integer id) {
        return veterinarioRepository.existsById(id);
    }

    public String asignarEspecialidad(int idVet, int idEspecialidad){
        if(!veterinarioRepository.existsById(idVet)){
            return "El Veterinario ingresado no existe";
        }else if(!especialidadRepository.existsById(idEspecialidad)){
            return "La especialidad ingresada no existe";
        }else{
            Veterinario veterinario = veterinarioRepository.findById(idVet).get();
            Especialidad especialidad = especialidadRepository.findById(idEspecialidad).get();
            veterinario.setEspecialidad(especialidad);
            veterinarioRepository.save(veterinario);
            return "Especialidad asignada correctamente";
        }
    }
}