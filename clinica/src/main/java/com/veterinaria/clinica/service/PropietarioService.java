package com.veterinaria.clinica.service;

import com.veterinaria.clinica.dto.PropietarioDto;
import java.util.List;

public interface PropietarioService {
    List<PropietarioDto> findAll();
    PropietarioDto findById(Long id);
    PropietarioDto save(PropietarioDto dto);
    PropietarioDto update(Long id, PropietarioDto dto);
    void delete(Long id);
}