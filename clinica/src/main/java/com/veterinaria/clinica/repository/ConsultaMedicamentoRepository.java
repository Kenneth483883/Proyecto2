package com.veterinaria.clinica.repository;

import com.veterinaria.clinica.models.ConsultaMedicamento;
import com.veterinaria.clinica.models.ConsultaMedicamentoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaMedicamentoRepository extends JpaRepository<ConsultaMedicamento, ConsultaMedicamentoId> {

}