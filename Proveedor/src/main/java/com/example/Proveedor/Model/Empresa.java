package com.example.Proveedor.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Empresa {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idEmpresa;
    private String nombre;
    private String contacto;
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy="empresa")
    private List<Proveedor> proveedores;

    public Empresa() {
        this.idEmpresa = 0;
        this.nombre = "";
        this.contacto = "";
        this.email = "";
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    





    

    
    

}
