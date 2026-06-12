package com.example.Movimientos.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Movimientos.Model.Movimiento;
import com.example.Movimientos.Service.MovimientoService;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    @Autowired
    private MovimientoService movimientoService;

    // LISTAR
    @GetMapping
    public List<Movimiento> listar() {
        return movimientoService.listarMovimientos();
    }

    // POR ID
    @GetMapping("/{id}")
    public Movimiento obtener(
            @PathVariable Integer id) {

        return movimientoService.obtenerMovimiento(id);
    }

    // HISTORIAL PRODUCTO
    @GetMapping("/producto/{productoId}")
    public List<Movimiento> historial(
            @PathVariable Integer productoId) {

        return movimientoService.historialProducto(productoId);
    }

    // GUARDAR
    @PostMapping
    public Movimiento guardar(
            @RequestBody Movimiento movimiento) {

        return movimientoService.guardarMovimiento(movimiento);
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public void eliminar(
            @PathVariable Integer id) {

        movimientoService.eliminarMovimiento(id);
    }

}
