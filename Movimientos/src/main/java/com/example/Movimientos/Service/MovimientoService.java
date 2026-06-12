package com.example.Movimientos.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.Movimientos.Model.Movimiento;
import com.example.Movimientos.Repository.MovimientoRepository;

@Service
public class MovimientoService {
    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String INVENTORY_URL =
        "http://localhost:8087/productos/";


    public List<Movimiento> listarMovimientos() {
        return movimientoRepository.findAll();
    }

    public Movimiento obtenerMovimiento(Integer id) {

        return movimientoRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Movimiento no encontrado"));
    }

    public List<Movimiento> historialProducto(Integer productoId) {

        return movimientoRepository.findByIdProducto(productoId);
    }

    public Movimiento guardarMovimiento(Movimiento movimiento) {

        try {

            restTemplate.getForObject(
                INVENTORY_URL + movimiento.getIdProducto(),
                Object.class);

        } catch (RestClientException e) {

            throw new RuntimeException(
                "Producto no encontrado");
        }

        // ACTUALIZAR STOCK
        restTemplate.put(
            INVENTORY_URL
                + movimiento.getIdProducto()
                + "/stock?cantidad="
                + movimiento.getCantidad()
                + "&tipo="
                + movimiento.getTipo(),
            null
        );
        
        return movimientoRepository.save(movimiento);
    }

    // ELIMINAR
    public void eliminarMovimiento(Integer id) {

        movimientoRepository.findById(id)
            .orElseThrow(() ->
                new RuntimeException("Movimiento no encontrado"));

        movimientoRepository.deleteById(id);
    }

}
