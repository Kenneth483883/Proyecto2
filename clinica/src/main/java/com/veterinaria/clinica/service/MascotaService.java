package com.veterinaria.clinica.service;

import com.veterinaria.clinica.dto.MascotaDto;
import java.util.List;

public interface MascotaService {
    List<MascotaDto> findAll();
    MascotaDto findById(Long id);
    List<MascotaDto> findByPropietarioId(Long propietarioId);
    MascotaDto save(MascotaDto dto);
    MascotaDto update(Long id, MascotaDto dto);
    void delete(Long id);
}