package com.example.diagnosis_service;

import com.example.diagnosis_service.model.ArchivoAdjunto;
import com.example.diagnosis_service.model.Diagnostico;
import com.example.diagnosis_service.repository.ArchivoAdjuntoRepository;
import com.example.diagnosis_service.repository.DiagnosticoRepository;
import com.example.diagnosis_service.service.DiagnosticoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DiagnosticoServiceTest {

    @Mock
    private DiagnosticoRepository diagnosticoRepository;

    @Mock
    private ArchivoAdjuntoRepository archivoRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private DiagnosticoService diagnosticoService;

    @Test
    public void testRegistrarDiagnosticoExitoso() {
        Diagnostico d = new Diagnostico();
        d.setCitaId(1);
        d.setSintomas("Tos");
        d.setDiagnostico("Bronquitis");

        when(restTemplate.getForObject(
            "http://localhost:8084/api/citas/validar/1", Boolean.class))
            .thenReturn(true);
        when(diagnosticoRepository.save(d)).thenReturn(d);

        Diagnostico result = diagnosticoService.registrarDiagnostico(d);

        assertNotNull(result);
        verify(diagnosticoRepository, times(1)).save(d);
    }

    @Test
    public void testRegistrarDiagnosticoCitaInexistente() {
        Diagnostico d = new Diagnostico();
        d.setCitaId(999);

        when(restTemplate.getForObject(
            "http://localhost:8084/api/citas/validar/999", Boolean.class))
            .thenReturn(false);

        assertThrows(RuntimeException.class,
            () -> diagnosticoService.registrarDiagnostico(d));
        verify(diagnosticoRepository, never()).save(any());
    }

    @Test
    public void testListarDiagnosticos() {
        when(diagnosticoRepository.findAll())
            .thenReturn(Arrays.asList(new Diagnostico(), new Diagnostico()));

        List<Diagnostico> result = diagnosticoService.listarDiagnosticos();

        assertEquals(2, result.size());
        verify(diagnosticoRepository, times(1)).findAll();
    }

    @Test
    public void testDiagnosticosPorMascota() {
        Diagnostico d = new Diagnostico();
        d.setPetId(1);
        when(diagnosticoRepository.findByPetId(1))
            .thenReturn(Arrays.asList(d));

        List<Diagnostico> result = diagnosticoService.diagnosticosPorMascota(1);

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getPetId());
    }

    @Test
    public void testAgregarArchivo() {
        ArchivoAdjunto archivo = new ArchivoAdjunto();
        archivo.setDiagId(1);
        archivo.setTipo("IMAGEN");
        when(archivoRepository.save(archivo)).thenReturn(archivo);

        ArchivoAdjunto result = diagnosticoService.agregarArchivo(archivo);

        assertNotNull(result);
        verify(archivoRepository, times(1)).save(archivo);
    }
}
