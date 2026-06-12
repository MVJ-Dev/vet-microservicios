package com.example.diagnosis_service.repository;

import com.example.diagnosis_service.model.ArchivoAdjunto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ArchivoAdjuntoRepository extends JpaRepository<ArchivoAdjunto, Integer> {
    List<ArchivoAdjunto> findByDiagId(Integer diagId);
}