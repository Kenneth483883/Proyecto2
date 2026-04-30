package com.veterinaria.clinica.repository;

import com.veterinaria.clinica.models.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
    List<Veterinario> findByEspecialidad(String especialidad);
}