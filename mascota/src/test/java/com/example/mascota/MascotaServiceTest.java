package com.example.mascota;

import com.example.mascota.Model.Mascota;
import com.example.mascota.Repository.MascotaRepository;
import com.example.mascota.Repository.InformacionRepository;
import com.example.mascota.Service.MascotaService;
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
public class MascotaServiceTest {

    @Mock
    private MascotaRepository mascotaRepository;

    @Mock
    private InformacionRepository informacionRepository;

    @InjectMocks
    private MascotaService mascotaService;

    @Test
    public void testAlmacenarMascotaNueva() {
        Mascota mascota = new Mascota();
        mascota.setNombre("Firulais");
        when(mascotaRepository.findByNombre("Firulais")).thenReturn(null);

        String result = mascotaService.almacenarMascota(mascota);

        verify(mascotaRepository, times(1)).save(mascota);
        assertNotNull(result);
    }

    @Test
    public void testAlmacenarMascotaExistente() {
        Mascota mascota = new Mascota();
        mascota.setNombre("Firulais");
        when(mascotaRepository.findByNombre("Firulais")).thenReturn(mascota);

        String result = mascotaService.almacenarMascota(mascota);

        verify(mascotaRepository, never()).save(any());
        assertNotNull(result);
    }

    @Test
    public void testListarMascota() {
        when(mascotaRepository.findAll())
            .thenReturn(Arrays.asList(new Mascota(), new Mascota()));

        List<Mascota> result = mascotaService.listarMascota();

        assertEquals(2, result.size());
        verify(mascotaRepository, times(1)).findAll();
    }

    @Test
    public void testObtenerMascota() {
        Mascota mascota = new Mascota();
        mascota.setIdMascota(1);
        when(mascotaRepository.findById(1)).thenReturn(Optional.of(mascota));

        Mascota result = mascotaService.obtenerMascota(1);

        assertNotNull(result);
        assertEquals(1, result.getIdMascota());
    }

    @Test
    public void testValidarMascotaExiste() {
        when(mascotaRepository.existsById(1)).thenReturn(true);

        boolean result = mascotaService.validarMascota(1);

        assertTrue(result);
    }

    @Test
    public void testValidarMascotaNoExiste() {
        when(mascotaRepository.existsById(999)).thenReturn(false);

        boolean result = mascotaService.validarMascota(999);

        assertFalse(result);
    }
}
