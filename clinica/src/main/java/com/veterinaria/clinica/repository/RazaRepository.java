package com.veterinaria.clinica.repository;

import com.veterinaria.clinica.models.Raza;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RazaRepository extends JpaRepository<Raza, Long> {
    List<Raza> findByEspecieId(Long especieId);
}