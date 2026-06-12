package com.example.appointment_service.repository;

import com.example.appointment_service.model.HistorialEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HistorialEstadoRepository extends JpaRepository<HistorialEstado, Integer> {
    List<HistorialEstado> findByCitaId(Integer citaId);
}