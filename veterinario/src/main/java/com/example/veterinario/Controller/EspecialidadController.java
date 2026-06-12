package com.example.veterinario.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.veterinario.Model.Especialidad;
import com.example.veterinario.Service.EspecialidadService;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadController {
    @Autowired
    private EspecialidadService especialidadService;

    @PostMapping
    public String almacenar(@RequestBody Especialidad especialidad){
        return especialidadService.almacenarEspecialidad(especialidad);
    }

    @GetMapping
    public List<Especialidad> listar(){
        return especialidadService.listarEspecialidad();
    }

}
