package com.veterinaria.clinica.service;

import com.veterinaria.clinica.dto.MedicamentoDto;
import java.util.List;

public interface MedicamentoService {
    List<MedicamentoDto> findAll();
    MedicamentoDto findById(Long id);
    MedicamentoDto save(MedicamentoDto dto);
    MedicamentoDto update(Long id, MedicamentoDto dto);
    void delete(Long id);
}