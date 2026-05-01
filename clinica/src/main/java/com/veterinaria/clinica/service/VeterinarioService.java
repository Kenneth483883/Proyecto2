package com.veterinaria.clinica.service;

import com.veterinaria.clinica.dto.VeterinarioDto;
import java.util.List;

public interface VeterinarioService {
    List<VeterinarioDto> findAll();
    VeterinarioDto findById(Long id);
    VeterinarioDto save(VeterinarioDto dto);
    VeterinarioDto update(Long id, VeterinarioDto dto);
    void delete(Long id);
}