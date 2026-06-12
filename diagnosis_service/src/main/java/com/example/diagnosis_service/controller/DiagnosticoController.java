package com.example.diagnosis_service.controller;

import com.example.diagnosis_service.model.Diagnostico;
import com.example.diagnosis_service.model.ArchivoAdjunto;
import com.example.diagnosis_service.service.DiagnosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/diagnosticos")
public class DiagnosticoController {

    @Autowired
    private DiagnosticoService diagnosticoService;

    @PostMapping
    public ResponseEntity<?> registrarDiagnostico(@RequestBody Diagnostico d) {
        try {
            return ResponseEntity.ok(diagnosticoService.registrarDiagnostico(d));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Diagnostico> listarDiagnosticos() {
        return diagnosticoService.listarDiagnosticos();
    }

    // Endpoints específicos ANTES del /{id}
    @GetMapping("/validar/{id}")
    public ResponseEntity<Boolean> validarDiagnostico(@PathVariable Integer id) {
        return ResponseEntity.ok(diagnosticoService.validarDiagnostico(id));
    }

    @GetMapping("/mascota/{petId}")
    public List<Diagnostico> diagnosticosPorMascota(@PathVariable Integer petId) {
        return diagnosticoService.diagnosticosPorMascota(petId);
    }

    @GetMapping("/cita/{citaId}")
    public List<Diagnostico> diagnosticosPorCita(@PathVariable Integer citaId) {
        return diagnosticoService.diagnosticosPorCita(citaId);
    }

    @PostMapping("/archivos")
    public ResponseEntity<?> agregarArchivo(@RequestBody ArchivoAdjunto archivo) {
        try {
            return ResponseEntity.ok(diagnosticoService.agregarArchivo(archivo));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{diagId}/archivos")
    public List<ArchivoAdjunto> archivosPorDiagnostico(@PathVariable Integer diagId) {
        return diagnosticoService.archivosPorDiagnostico(diagId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerDiagnostico(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(diagnosticoService.obtenerDiagnostico(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}