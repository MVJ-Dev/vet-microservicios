package com.example.veterinario.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEspecialidad;

    private String nombre;
    private String descripcion;
    
//-----------------------------------------------------------

    @JsonIgnore
    @OneToMany(mappedBy="especialidad")
    private List<Veterinario> veterinario;

//------------------------------------------------
    public Especialidad() {
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Veterinario> getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(List<Veterinario> veterinario) {
        this.veterinario = veterinario;
    }

    
    
    

    

}
