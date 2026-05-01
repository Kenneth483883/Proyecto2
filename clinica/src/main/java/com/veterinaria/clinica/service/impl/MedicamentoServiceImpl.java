package com.veterinaria.clinica.service.impl;

import com.veterinaria.clinica.dto.MedicamentoDto;
import com.veterinaria.clinica.models.Medicamento;
import com.veterinaria.clinica.repository.MedicamentoRepository;
import com.veterinaria.clinica.service.MedicamentoService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicamentoServiceImpl implements MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;

    public MedicamentoServiceImpl(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }

    @Override
    public List<MedicamentoDto> findAll() {
        return medicamentoRepository.findAll()
                .stream()
                .map(MedicamentoServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public MedicamentoDto findById(Long id) {
        Medicamento medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado con id: " + id));
        return mapToDto(medicamento);
    }

    @Override
    public MedicamentoDto save(MedicamentoDto dto) {
        return mapToDto(medicamentoRepository.save(mapToEntity(dto)));
    }

    @Override
    public MedicamentoDto update(Long id, MedicamentoDto dto) {
        Medicamento medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicamento no encontrado con id: " + id));
        medicamento.setNombre(dto.getNombre());
        medicamento.setDescripcion(dto.getDescripcion());
        medicamento.setUnidadDosis(dto.getUnidadDosis());
        return mapToDto(medicamentoRepository.save(medicamento));
    }

    @Override
    public void delete(Long id) {
        medicamentoRepository.deleteById(id);
    }

    public static MedicamentoDto mapToDto(Medicamento m) {
        return MedicamentoDto.builder()
                .id(m.getId())
                .nombre(m.getNombre())
                .descripcion(m.getDescripcion())
                .unidadDosis(m.getUnidadDosis())
                .build();
    }

    public static Medicamento mapToEntity(MedicamentoDto dto) {
        return Medicamento.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .unidadDosis(dto.getUnidadDosis())
                .build();
    }
}