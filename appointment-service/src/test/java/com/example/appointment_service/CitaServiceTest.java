package com.example.appointment_service;

import com.example.appointment_service.model.Cita;
import com.example.appointment_service.model.HistorialEstado;
import com.example.appointment_service.repository.CitaRepository;
import com.example.appointment_service.repository.HistorialEstadoRepository;
import com.example.appointment_service.service.CitaService;
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
public class CitaServiceTest {

    @Mock
    private CitaRepository citaRepository;

    @Mock
    private HistorialEstadoRepository historialRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CitaService citaService;

    @Test
    public void testAgendarCitaExitosa() {
        Cita cita = new Cita();
        cita.setPetId(1);
        cita.setVetId(1);
        cita.setMotivo("Control");

        when(restTemplate.getForObject(
            "http://localhost:8082/mascotas/validar/1", Boolean.class))
            .thenReturn(true);
        when(restTemplate.getForObject(
            "http://localhost:8083/veterinarios/validar/1", Boolean.class))
            .thenReturn(true);
        when(citaRepository.save(cita)).thenReturn(cita);

        Cita result = citaService.agendarCita(cita);

        assertNotNull(result);
        verify(citaRepository, times(1)).save(cita);
    }

    @Test
    public void testAgendarCitaMascotaInexistente() {
        Cita cita = new Cita();
        cita.setPetId(999);
        cita.setVetId(1);

        when(restTemplate.getForObject(
            "http://localhost:8082/mascotas/validar/999", Boolean.class))
            .thenReturn(false);

        assertThrows(RuntimeException.class,
            () -> citaService.agendarCita(cita));
        verify(citaRepository, never()).save(any());
    }

    @Test
    public void testAgendarCitaVeterinarioInexistente() {
        Cita cita = new Cita();
        cita.setPetId(1);
        cita.setVetId(999);

        when(restTemplate.getForObject(
            "http://localhost:8082/mascotas/validar/1", Boolean.class))
            .thenReturn(true);
        when(restTemplate.getForObject(
            "http://localhost:8083/veterinarios/validar/999", Boolean.class))
            .thenReturn(false);

        assertThrows(RuntimeException.class,
            () -> citaService.agendarCita(cita));
        verify(citaRepository, never()).save(any());
    }

    @Test
    public void testListarCitas() {
        when(citaRepository.findAll())
            .thenReturn(Arrays.asList(new Cita(), new Cita()));

        List<Cita> result = citaService.listarCitas();

        assertEquals(2, result.size());
        verify(citaRepository, times(1)).findAll();
    }

    @Test
    public void testValidarCitaExiste() {
        when(citaRepository.existsById(1)).thenReturn(true);

        boolean result = citaService.validarCita(1);

        assertTrue(result);
    }

    @Test
    public void testValidarCitaNoExiste() {
        when(citaRepository.existsById(999)).thenReturn(false);

        boolean result = citaService.validarCita(999);

        assertFalse(result);
    }

    @Test
    public void testCambiarEstado() {
        Cita cita = new Cita();
        cita.setCitaId(1);
        cita.setEstado("PENDIENTE");

        when(citaRepository.findById(1)).thenReturn(Optional.of(cita));
        when(citaRepository.save(any())).thenReturn(cita);
        when(historialRepository.save(any())).thenReturn(new HistorialEstado());

        Cita result = citaService.cambiarEstado(1, "CONFIRMADA", "Cliente confirmó");

        assertEquals("CONFIRMADA", result.getEstado());
        verify(historialRepository, times(1)).save(any());
        verify(citaRepository, times(1)).save(cita);
    }
}
