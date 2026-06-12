package com.example.Inventario.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Inventario.Model.Inventario;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Integer>{
    Inventario findByNombre(String nombre);

}
