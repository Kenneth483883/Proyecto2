package com.veterinaria.clinica.service;

import com.veterinaria.clinica.dto.ConsultaDto;
import java.util.List;

public interface ConsultaService {
    List<ConsultaDto> findAll();
    ConsultaDto findById(Long id);
    List<ConsultaDto> findByMascotaId(Long mascotaId);
    List<ConsultaDto> findByVeterinarioId(Long veterinarioId);
    ConsultaDto save(ConsultaDto dto);
    ConsultaDto update(Long id, ConsultaDto dto);
    void delete(Long id);
}