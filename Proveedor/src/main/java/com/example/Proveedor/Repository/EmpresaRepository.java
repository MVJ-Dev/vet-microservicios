package com.example.Proveedor.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Proveedor.Model.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer>{
    Empresa findByNombre(String nombre);

}
