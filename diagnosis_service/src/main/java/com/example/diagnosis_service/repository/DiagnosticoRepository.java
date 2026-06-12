package com.example.diagnosis_service.repository;

import com.example.diagnosis_service.model.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Integer> {
    List<Diagnostico> findByPetId(Integer petId);
    List<Diagnostico> findByCitaId(Integer citaId);
}