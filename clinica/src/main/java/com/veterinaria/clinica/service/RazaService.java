package com.veterinaria.clinica.service;

import com.veterinaria.clinica.dto.RazaDto;
import java.util.List;

public interface RazaService {
    List<RazaDto> findAll();
    RazaDto findById(Long id);
    List<RazaDto> findByEspecieId(Long especieId);
    RazaDto save(RazaDto dto);
    RazaDto update(Long id, RazaDto dto);
    void delete(Long id);
}