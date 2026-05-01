package com.veterinaria.clinica.service.impl;

import com.veterinaria.clinica.dto.EspecieDto;
import com.veterinaria.clinica.models.Especie;
import com.veterinaria.clinica.repository.EspecieRepository;
import com.veterinaria.clinica.service.EspecieService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EspecieServiceImpl implements EspecieService {

    private final EspecieRepository especieRepository;

    public EspecieServiceImpl(EspecieRepository especieRepository) {
        this.especieRepository = especieRepository;
    }

    @Override
    public List<EspecieDto> findAll() {
        return especieRepository.findAll()
                .stream()
                .map(EspecieServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EspecieDto findById(Long id) {
        Especie especie = especieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especie no encontrada con id: " + id));
        return mapToDto(especie);
    }

    @Override
    public EspecieDto save(EspecieDto dto) {
        Especie especie = mapToEntity(dto);
        return mapToDto(especieRepository.save(especie));
    }

    @Override
    public EspecieDto update(Long id, EspecieDto dto) {
        Especie especie = especieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Especie no encontrada con id: " + id));
        especie.setNombre(dto.getNombre());
        especie.setDescripcion(dto.getDescripcion());
        return mapToDto(especieRepository.save(especie));
    }

    @Override
    public void delete(Long id) {
        especieRepository.deleteById(id);
    }

    public static EspecieDto mapToDto(Especie e) {
        return EspecieDto.builder()
                .id(e.getId())
                .nombre(e.getNombre())
                .descripcion(e.getDescripcion())
                .build();
    }

    public static Especie mapToEntity(EspecieDto dto) {
        return Especie.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .build();
    }
}