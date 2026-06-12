package com.usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.model.Usuario;
import com.usuario.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> consultarUsuario(){
        return this.usuarioService.listar();
    }

    @PostMapping
    public String guardarUsuario(@RequestBody Usuario usuario){
        return this.usuarioService.almacenar(usuario);
    }

    @GetMapping("/validar/{id}")
    public ResponseEntity<Boolean> validarUsuario(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.validarUsuario(id));
    }

    @PutMapping("/{id}")
    public void modificarUsuario(@PathVariable int id, @RequestBody Usuario usuario){
        this.usuarioService.modificar(usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminacionUsuario(@PathVariable int id){
        this.usuarioService.eliminar(id);
    }
}