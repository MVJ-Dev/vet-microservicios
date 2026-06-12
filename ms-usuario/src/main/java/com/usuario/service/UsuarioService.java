package com.usuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usuario.model.Usuario;
import com.usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    public String almacenar(Usuario usuario){
        this.usuarioRepository.save(usuario);
        return "Usuario registrado";
    }

    public Usuario modificar(Usuario usuario){
        return this.usuarioRepository.save(usuario);
    }

    public void eliminar(int id){
        this.usuarioRepository.deleteById(id);
    }
    
    public boolean validarUsuario(Integer id) {
        return usuarioRepository.existsById(id);
    }
}