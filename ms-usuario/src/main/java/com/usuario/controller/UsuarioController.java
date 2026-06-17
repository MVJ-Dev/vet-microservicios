package com.usuario.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.usuario.model.Usuario;
import com.usuario.service.JwtService;
import com.usuario.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<Usuario> consultarUsuario() {
        return this.usuarioService.listar();
    }

    @PostMapping
    public String guardarUsuario(@RequestBody Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return this.usuarioService.almacenar(usuario);
    }

    @GetMapping("/validar/{id}")
    public ResponseEntity<Boolean> validarUsuario(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.validarUsuario(id));
    }

    @PutMapping("/{id}")
    public void modificarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        this.usuarioService.modificar(usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminacionUsuario(@PathVariable int id) {
        this.usuarioService.eliminar(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    usuario.getEmail(),
                    usuario.getPassword()
                )
            );
            String token = jwtService.generarToken(usuario.getEmail());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }
}