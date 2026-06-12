package com.example.appointment_service.repository;

import com.example.appointment_service.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Integer> {
    List<Cita> findByPetId(Integer petId);
}