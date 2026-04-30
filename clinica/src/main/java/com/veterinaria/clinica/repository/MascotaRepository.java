package com.veterinaria.clinica.repository;

import com.veterinaria.clinica.models.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByPropietarioId(Long propietarioId);
    List<Mascota> findByRazaId(Long razaId);
}