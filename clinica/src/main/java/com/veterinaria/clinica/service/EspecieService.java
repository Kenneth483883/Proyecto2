package com.veterinaria.clinica.service;

import com.veterinaria.clinica.dto.EspecieDto;
import java.util.List;

public interface EspecieService {
    List<EspecieDto> findAll();
    EspecieDto findById(Long id);
    EspecieDto save(EspecieDto dto);
    EspecieDto update(Long id, EspecieDto dto);
    void delete(Long id);
}