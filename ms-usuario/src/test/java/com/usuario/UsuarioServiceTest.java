package com.usuario;

import com.usuario.model.Usuario;
import com.usuario.repository.UsuarioRepository;
import com.usuario.service.UsuarioService;
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
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    public void testListar() {
        Usuario u1 = new Usuario();
        u1.setNombre("Juan");
        Usuario u2 = new Usuario();
        u2.setNombre("Maria");
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(u1, u2));

        List<Usuario> result = usuarioService.listar();

        assertEquals(2, result.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    public void testAlmacenar() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setEmail("juan@mail.com");

        String result = usuarioService.almacenar(usuario);

        verify(usuarioRepository, times(1)).save(usuario);
        assertNotNull(result);
    }

    @Test
    public void testModificar() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombre("Juan Actualizado");
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario result = usuarioService.modificar(usuario);

        assertEquals("Juan Actualizado", result.getNombre());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    public void testEliminar() {
        doNothing().when(usuarioRepository).deleteById(1);

        usuarioService.eliminar(1);

        verify(usuarioRepository, times(1)).deleteById(1);
    }

    @Test
    public void testValidarUsuarioExiste() {
        when(usuarioRepository.existsById(1)).thenReturn(true);

        boolean result = usuarioService.validarUsuario(1);

        assertTrue(result);
    }

    @Test
    public void testValidarUsuarioNoExiste() {
        when(usuarioRepository.existsById(999)).thenReturn(false);

        boolean result = usuarioService.validarUsuario(999);

        assertFalse(result);
    }
}
