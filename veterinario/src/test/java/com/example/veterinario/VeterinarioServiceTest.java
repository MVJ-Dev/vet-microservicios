package com.example.veterinario;

import com.example.veterinario.Model.Veterinario;
import com.example.veterinario.Repository.VeterinarioRepository;
import com.example.veterinario.Repository.EspecialidadRepository;
import com.example.veterinario.Service.VeterinarioService;
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
public class VeterinarioServiceTest {

    @Mock
    private VeterinarioRepository veterinarioRepository;

    @Mock
    private EspecialidadRepository especialidadRepository;

    @InjectMocks
    private VeterinarioService veterinarioService;

    @Test
    public void testAlmacenarVeterinarioNuevo() {
        Veterinario vet = new Veterinario();
        vet.setNombre("Dr. Garcia");
        when(veterinarioRepository.findByNombre("Dr. Garcia")).thenReturn(null);

        String result = veterinarioService.almacenarVeterinario(vet);

        verify(veterinarioRepository, times(1)).save(vet);
        assertNotNull(result);
        assertTrue(result.contains("Dr. Garcia"));
    }

    @Test
    public void testAlmacenarVeterinarioExistente() {
        Veterinario vet = new Veterinario();
        vet.setNombre("Dr. Garcia");
        when(veterinarioRepository.findByNombre("Dr. Garcia")).thenReturn(vet);

        String result = veterinarioService.almacenarVeterinario(vet);

        verify(veterinarioRepository, never()).save(any());
        assertNotNull(result);
    }

    @Test
    public void testListarVeterinario() {
        when(veterinarioRepository.findAll())
            .thenReturn(Arrays.asList(new Veterinario(), new Veterinario()));

        List<Veterinario> result = veterinarioService.listarVeterianrio();

        assertEquals(2, result.size());
        verify(veterinarioRepository, times(1)).findAll();
    }

    @Test
    public void testObtenerVeterinario() {
        Veterinario vet = new Veterinario();
        vet.setIdVet(1);
        when(veterinarioRepository.findById(1)).thenReturn(Optional.of(vet));

        Veterinario result = veterinarioService.obteneVeterinario(1);

        assertNotNull(result);
        assertEquals(1, result.getIdVet());
    }

    @Test
    public void testValidarVeterinarioExiste() {
        when(veterinarioRepository.existsById(1)).thenReturn(true);
        assertTrue(veterinarioService.validarVeterinario(1));
    }

    @Test
    public void testValidarVeterinarioNoExiste() {
        when(veterinarioRepository.existsById(999)).thenReturn(false);
        assertFalse(veterinarioService.validarVeterinario(999));
    }
}
