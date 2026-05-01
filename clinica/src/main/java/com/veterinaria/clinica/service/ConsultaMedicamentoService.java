package com.veterinaria.clinica.service;

import com.veterinaria.clinica.dto.ConsultaMedicamentoDto;
import java.util.List;

public interface ConsultaMedicamentoService {
    List<ConsultaMedicamentoDto> findAll();
    List<ConsultaMedicamentoDto> findByConsultaId(Long consultaId);
    ConsultaMedicamentoDto save(ConsultaMedicamentoDto dto);
    void delete(Long consultaId, Long medicamentoId);
}