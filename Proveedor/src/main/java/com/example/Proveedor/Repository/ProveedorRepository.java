package com.example.Proveedor.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Proveedor.Model.Proveedor;

@Repository

public interface ProveedorRepository extends JpaRepository <Proveedor, Integer> {
    Proveedor findByNombre(String nombre);
    

}
