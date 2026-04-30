package com.veterinaria.clinica.repository;

import com.veterinaria.clinica.models.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
}
