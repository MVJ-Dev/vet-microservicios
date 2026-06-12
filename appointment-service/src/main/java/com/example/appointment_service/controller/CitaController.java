package com.example.appointment_service.controller;

import com.example.appointment_service.model.Cita;
import com.example.appointment_service.model.HistorialEstado;
import com.example.appointment_service.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @PostMapping
    public ResponseEntity<?> agendarCita(@RequestBody Cita cita) {
        try {
            return ResponseEntity.ok(citaService.agendarCita(cita));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Cita> listarCitas() {
        return citaService.listarCitas();
    }

    @GetMapping("/validar/{id}")
    public ResponseEntity<Boolean> validarCita(@PathVariable Integer id) {
        return ResponseEntity.ok(citaService.validarCita(id));
    }

    @GetMapping("/mascota/{petId}")
    public List<Cita> citasPorMascota(@PathVariable Integer petId) {
        return citaService.citasPorMascota(petId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCita(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(citaService.obtenerCita(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/historial")
    public List<HistorialEstado> historialCita(@PathVariable Integer id) {
        return citaService.historialCita(id);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(
            @PathVariable Integer id,
            @RequestBody Map<String, String> body) {
        try {
            return ResponseEntity.ok(citaService.cambiarEstado(
                    id, body.get("estado"), body.get("observacion")));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}