package com.example.veterinario.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.veterinario.Model.Veterinario;
import com.example.veterinario.Service.VeterinarioService;

@RestController
@RequestMapping("/veterinarios")
public class VeterinarioController {

    @Autowired
    private VeterinarioService veterinarioService;

    @PostMapping
    public String almacenar(@RequestBody Veterinario veterinario){
        return veterinarioService.almacenarVeterinario(veterinario);
    }

    @GetMapping
    public List<Veterinario> listarVeterinario(){
        return veterinarioService.listarVeterianrio();
    }

    @GetMapping("/validar/{id}")
    public ResponseEntity<Boolean> validarVeterinario(@PathVariable Integer id) {
        return ResponseEntity.ok(veterinarioService.validarVeterinario(id));
    }

    @GetMapping("/{id}")
    public Veterinario obtenerProveedor(@PathVariable Integer id){
        return veterinarioService.obteneVeterinario(id);
    }

    @PutMapping("/{id}")
    public void actualizarVet(@PathVariable Integer id, @RequestBody Veterinario veterinario){
        this.veterinarioService.actualizarVeterinario(id, veterinario);
    }

    @DeleteMapping("/{id}")
    public void eliminarVeterinario(@PathVariable Integer id){
        this.veterinarioService.eliminarVeterinario(id);
    }

    @PostMapping("/asignar/{idVet}/{idEspecialidad}")
    public String asignarEspecialidad(@PathVariable Integer idVet, @PathVariable Integer idEspecialidad){
        return veterinarioService.asignarEspecialidad(idVet, idEspecialidad);
    }
}