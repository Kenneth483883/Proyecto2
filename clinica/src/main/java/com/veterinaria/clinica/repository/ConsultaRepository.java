package com.veterinaria.clinica.repository;

import com.veterinaria.clinica.models.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByMascotaId(Long mascotaId);
    List<Consulta> findByVeterinarioId(Long veterinarioId);
    List<Consulta> findByFechaConsultaBetween(LocalDate desde, LocalDate hasta);
}