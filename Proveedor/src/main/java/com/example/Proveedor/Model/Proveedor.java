package com.example.Proveedor.Model;



import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Proveedor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int idProveedor;
    private String nombre;
    private String telefono;
    private String email;
    private String status;
    private LocalDateTime fechaRegistro;


    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;


    public Proveedor() {
        this.fechaRegistro = LocalDateTime.now();
    }


    public int getIdProveedor() {
        return idProveedor;
    }


    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getTelefono() {
        return telefono;
    }


    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }


    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }


    public Empresa getEmpresa() {
        return empresa;
    }


    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    

    






    

    

    

    



}
