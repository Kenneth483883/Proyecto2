package com.veterinaria.clinica.service.impl;

import com.veterinaria.clinica.dto.ConsultaMedicamentoDto;
import com.veterinaria.clinica.models.Consulta;
import com.veterinaria.clinica.models.ConsultaMedicamento;
import com.veterinaria.clinica.models.ConsultaMedicamentoId;
import com.veterinaria.clinica.models.Medicamento;
import com.veterinaria.clinica.repository.ConsultaMedicamentoRepository;
import com.veterinaria.clinica.repository.ConsultaRepository;
import com.veterinaria.clinica.repository.MedicamentoRepository;
import com.veterinaria.clinica.service.ConsultaMedicamentoService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultaMedicamentoServiceImpl implements ConsultaMedicamentoService {

    private final ConsultaMedicamentoRepository consultaMedicamentoRepository;
    private final ConsultaRepository consultaRepository;
    private final MedicamentoRepository medicamentoRepository;

    public ConsultaMedicamentoServiceImpl(ConsultaMedicamentoRepository consultaMedicamentoRepository,
                                          ConsultaRepository consultaRepository,
                                          MedicamentoRepository medicamentoRepository) {
        this.consultaMedicamentoRepository = consultaMedicamentoRepository;
        this.consultaRepository = consultaRepository;
        this.medicamentoRepository = medicamentoRepository;
    }

    @Override
    public List<ConsultaMedicamentoDto> findAll() {
        return consultaMedicamentoRepository.findAll()
                .stream()
                .map(ConsultaMedicamentoServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConsultaMedicamentoDto> findByConsultaId(Long consultaId) {
        return consultaMedicamentoRepository.findAll()
                .stream()
                .filter(cm -> cm.getConsulta().getId().equals(consultaId))
                .map(ConsultaMedicamentoServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ConsultaMedicamentoDto save(ConsultaMedicamentoDto dto) {
        Consulta consulta = consultaRepository.findById(dto.getConsultaId())
                .orElseThrow(() -> new RuntimeException("Consulta no encontrada con id: " + dto.getConsultaId()));
        Medicamento medicamento = medicamentoRepository.findById(dto.getMedicamentoId())
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado con id: " + dto.getMedicamentoId()));
        ConsultaMedicamentoId id = new ConsultaMedicamentoId(dto.getConsultaId(), dto.getMedicamentoId());
        ConsultaMedicamento cm = ConsultaMedicamento.builder()
                .id(id)
                .consulta(consulta)
                .medicamento(medicamento)
                .dosis(dto.getDosis())
                .duracionDias(dto.getDuracionDias())
                .build();
        return mapToDto(consultaMedicamentoRepository.save(cm));
    }

    @Override
    public void delete(Long consultaId, Long medicamentoId) {
        ConsultaMedicamentoId id = new ConsultaMedicamentoId(consultaId, medicamentoId);
        consultaMedicamentoRepository.deleteById(id);
    }

    public static ConsultaMedicamentoDto mapToDto(ConsultaMedicamento cm) {
        return ConsultaMedicamentoDto.builder()
                .consultaId(cm.getConsulta().getId())
                .medicamentoId(cm.getMedicamento().getId())
                .medicamentoNombre(cm.getMedicamento().getNombre())
                .dosis(cm.getDosis())
                .duracionDias(cm.getDuracionDias())
                .build();
    }
}