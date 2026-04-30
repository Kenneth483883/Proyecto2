package com.veterinaria.clinica.repository;

import com.veterinaria.clinica.models.Especie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecieRepository extends JpaRepository<Especie, Long> {
}
