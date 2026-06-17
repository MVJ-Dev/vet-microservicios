package com.example.Inventario;

import com.example.Inventario.Model.Inventario;
import com.example.Inventario.Repository.InventarioRepository;
import com.example.Inventario.Service.InventarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InventarioServiceTest {

    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private InventarioService inventarioService;

    @Test
    public void testListarProductos() {
        when(inventarioRepository.findAll())
            .thenReturn(Arrays.asList(new Inventario(), new Inventario()));

        List<Inventario> result = inventarioService.listarProductos();

        assertEquals(2, result.size());
        verify(inventarioRepository, times(1)).findAll();
    }

    @Test
    public void testActualizarStockEntrada() {
        Inventario producto = new Inventario();
        producto.setIdProducto(1);
        producto.setStock(50);

        when(inventarioRepository.findById(1))
            .thenReturn(Optional.of(producto));
        when(inventarioRepository.save(any())).thenReturn(producto);

        Inventario result = inventarioService.actualizarStock(1, 20, "ENTRADA");

        assertEquals(70, result.getStock());
        verify(inventarioRepository, times(1)).save(producto);
    }

    @Test
    public void testActualizarStockSalida() {
        Inventario producto = new Inventario();
        producto.setIdProducto(1);
        producto.setStock(50);

        when(inventarioRepository.findById(1))
            .thenReturn(Optional.of(producto));
        when(inventarioRepository.save(any())).thenReturn(producto);

        Inventario result = inventarioService.actualizarStock(1, 10, "SALIDA");

        assertEquals(40, result.getStock());
        verify(inventarioRepository, times(1)).save(producto);
    }

    @Test
    public void testActualizarStockInsuficiente() {
        Inventario producto = new Inventario();
        producto.setIdProducto(1);
        producto.setStock(5);

        when(inventarioRepository.findById(1))
            .thenReturn(Optional.of(producto));

        assertThrows(RuntimeException.class,
            () -> inventarioService.actualizarStock(1, 100, "SALIDA"));
        verify(inventarioRepository, never()).save(any());
    }

    @Test
    public void testActualizarStockProductoNoEncontrado() {
        when(inventarioRepository.findById(999))
            .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
            () -> inventarioService.actualizarStock(999, 10, "ENTRADA"));
    }
}
