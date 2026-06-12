package com.example.mascota.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mascota.Model.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer>{
    Mascota findByNombre(String nombre);

}
