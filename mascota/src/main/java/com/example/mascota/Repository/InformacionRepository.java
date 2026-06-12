package com.example.mascota.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mascota.Model.Informacion;

@Repository
public interface InformacionRepository extends JpaRepository<Informacion, Integer>{
    Informacion findByMicroChip(String microChip);
}