package com.example.mascota.Controller;

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

import com.example.mascota.Model.Mascota;
import com.example.mascota.Service.MascotaService;

@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @PostMapping
    public String almacenar(@RequestBody Mascota mascota){
        return mascotaService.almacenarMascota(mascota);
    }

    @GetMapping
    public List<Mascota> listarMascota(){
        return mascotaService.listarMascota();
    }

    @GetMapping("/validar/{id}")
    public ResponseEntity<Boolean> validarMascota(@PathVariable Integer id) {
        return ResponseEntity.ok(mascotaService.validarMascota(id));
    }

    @GetMapping("/{id}")
    public Mascota obtenerMascota(@PathVariable Integer id){
        return mascotaService.obtenerMascota(id);
    }

    @PutMapping("/{id}")
    public void actualizarMascota(@PathVariable Integer id, @RequestBody Mascota mascota){
        this.mascotaService.actualizarMascota(id, mascota);
    }

    @DeleteMapping("/{id}")
    public void eliminarMascota(@PathVariable Integer id){
        this.mascotaService.eliminarMascota(id);
    }

    @PostMapping("/asignar/{idMascota}/{idInfo}")
    public String asignarEspecialidad(@PathVariable Integer idMascota, @PathVariable Integer idInfo){
        return mascotaService.asignarInfo(idMascota, idInfo);
    }
}