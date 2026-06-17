package com.example.Movimientos;

import com.example.Movimientos.Model.Movimiento;
import com.example.Movimientos.Repository.MovimientoRepository;
import com.example.Movimientos.Service.MovimientoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovimientoServiceTest {

    @Mock
    private MovimientoRepository movimientoRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private MovimientoService movimientoService;

    @Test
    public void testListarMovimientos() {
        when(movimientoRepository.findAll())
            .thenReturn(Arrays.asList(new Movimiento(), new Movimiento()));

        List<Movimiento> result = movimientoService.listarMovimientos();

        assertEquals(2, result.size());
        verify(movimientoRepository, times(1)).findAll();
    }

    @Test
    public void testGuardarMovimientoExitoso() {
        Movimiento movimiento = new Movimiento();
        movimiento.setIdProducto(1);
        movimiento.setCantidad(10);
        movimiento.setTipo("ENTRADA");

        when(restTemplate.getForObject(
            "http://localhost:8087/productos/1", Object.class))
            .thenReturn(new Object());
        doNothing().when(restTemplate).put(anyString(), any());
        when(movimientoRepository.save(movimiento)).thenReturn(movimiento);

        Movimiento result = movimientoService.guardarMovimiento(movimiento);

        assertNotNull(result);
        verify(movimientoRepository, times(1)).save(movimiento);
    }

    @Test
    public void testGuardarMovimientoProductoInexistente() {
        Movimiento movimiento = new Movimiento();
        movimiento.setIdProducto(999);
        movimiento.setCantidad(10);
        movimiento.setTipo("ENTRADA");

        when(restTemplate.getForObject(
            "http://localhost:8087/productos/999", Object.class))
            .thenThrow(new org.springframework.web.client.RestClientException("Error"));

        assertThrows(RuntimeException.class,
            () -> movimientoService.guardarMovimiento(movimiento));
        verify(movimientoRepository, never()).save(any());
    }

    @Test
    public void testHistorialProducto() {
        Movimiento m = new Movimiento();
        m.setIdProducto(1);
        when(movimientoRepository.findByIdProducto(1))
            .thenReturn(Arrays.asList(m));

        List<Movimiento> result = movimientoService.historialProducto(1);

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getIdProducto());
    }

    @Test
    public void testObtenerMovimiento() {
        Movimiento movimiento = new Movimiento();
        movimiento.setIdMovimiento(1);
        when(movimientoRepository.findById(1))
            .thenReturn(Optional.of(movimiento));

        Movimiento result = movimientoService.obtenerMovimiento(1);

        assertNotNull(result);
        assertEquals(1, result.getIdMovimiento());
    }
}
