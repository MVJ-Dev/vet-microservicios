package com.example.Proveedor;

import com.example.Proveedor.Model.Proveedor;
import com.example.Proveedor.Model.Empresa;
import com.example.Proveedor.Repository.ProveedorRepository;
import com.example.Proveedor.Repository.EmpresaRepository;
import com.example.Proveedor.Service.ProveedorService;
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
public class ProveedorServiceTest {

    @Mock
    private ProveedorRepository proveedorRepository;

    @Mock
    private EmpresaRepository empresaRepository;

    @InjectMocks
    private ProveedorService proveedorService;

    @Test
    public void testAlmacenarProveedorNuevo() {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre("MedVet");
        when(proveedorRepository.findByNombre("MedVet")).thenReturn(null);

        String result = proveedorService.almacenarProveedor(proveedor);

        verify(proveedorRepository, times(1)).save(proveedor);
        assertNotNull(result);
        assertTrue(result.contains("MedVet"));
    }

    @Test
    public void testAlmacenarProveedorExistente() {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre("MedVet");
        when(proveedorRepository.findByNombre("MedVet")).thenReturn(proveedor);

        String result = proveedorService.almacenarProveedor(proveedor);

        verify(proveedorRepository, never()).save(any());
        assertNotNull(result);
    }

    @Test
    public void testListarProveedor() {
        when(proveedorRepository.findAll())
            .thenReturn(Arrays.asList(new Proveedor(), new Proveedor()));

        List<Proveedor> result = proveedorService.listarProveedor();

        assertEquals(2, result.size());
        verify(proveedorRepository, times(1)).findAll();
    }

    @Test
    public void testObtenerProveedor() {
        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(1);
        when(proveedorRepository.findById(1)).thenReturn(Optional.of(proveedor));

        Proveedor result = proveedorService.obtenerProveedor(1);

        assertNotNull(result);
        assertEquals(1, result.getIdProveedor());
    }

    @Test
    public void testAsignarEmpresaExitosa() {
        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(1);
        Empresa empresa = new Empresa();
        empresa.setIdEmpresa(1);

        when(proveedorRepository.existsById(1)).thenReturn(true);
        when(empresaRepository.existsById(1)).thenReturn(true);
        when(proveedorRepository.findById(1)).thenReturn(Optional.of(proveedor));
        when(empresaRepository.findById(1)).thenReturn(Optional.of(empresa));
        when(proveedorRepository.save(any())).thenReturn(proveedor);

        String result = proveedorService.asignarEmpresa(1, 1);

        assertNotNull(result);
        verify(proveedorRepository, times(1)).save(any());
    }

    @Test
    public void testAsignarEmpresaProveedorNoExiste() {
        when(proveedorRepository.existsById(999)).thenReturn(false);

        String result = proveedorService.asignarEmpresa(999, 1);

        assertTrue(result.contains("no existe"));
        verify(proveedorRepository, never()).save(any());
    }
}
