package com.veterinaria.clinica.service.impl;

import com.veterinaria.clinica.dto.RazaDto;
import com.veterinaria.clinica.models.Especie;
import com.veterinaria.clinica.models.Raza;
import com.veterinaria.clinica.repository.EspecieRepository;
import com.veterinaria.clinica.repository.RazaRepository;
import com.veterinaria.clinica.service.RazaService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RazaServiceImpl implements RazaService {

    private final RazaRepository razaRepository;
    private final EspecieRepository especieRepository;

    public RazaServiceImpl(RazaRepository razaRepository, EspecieRepository especieRepository) {
        this.razaRepository = razaRepository;
        this.especieRepository = especieRepository;
    }

    @Override
    public List<RazaDto> findAll() {
        return razaRepository.findAll()
                .stream()
                .map(RazaServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RazaDto findById(Long id) {
        Raza raza = razaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Raza no encontrada con id: " + id));
        return mapToDto(raza);
    }

    @Override
    public List<RazaDto> findByEspecieId(Long especieId) {
        return razaRepository.findByEspecieId(especieId)
                .stream()
                .map(RazaServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RazaDto save(RazaDto dto) {
        Especie especie = especieRepository.findById(dto.getEspecieId())
                .orElseThrow(() -> new RuntimeException("Especie no encontrada con id: " + dto.getEspecieId()));
        Raza raza = Raza.builder()
                .nombre(dto.getNombre())
                .especie(especie)
                .build();
        return mapToDto(razaRepository.save(raza));
    }

    @Override
    public RazaDto update(Long id, RazaDto dto) {
        Raza raza = razaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Raza no encontrada con id: " + id));
        Especie especie = especieRepository.findById(dto.getEspecieId())
                .orElseThrow(() -> new RuntimeException("Especie no encontrada con id: " + dto.getEspecieId()));
        raza.setNombre(dto.getNombre());
        raza.setEspecie(especie);
        return mapToDto(razaRepository.save(raza));
    }

    @Override
    public void delete(Long id) {
        razaRepository.deleteById(id);
    }

    public static RazaDto mapToDto(Raza r) {
        return RazaDto.builder()
                .id(r.getId())
                .nombre(r.getNombre())
                .especieId(r.getEspecie().getId())
                .especieNombre(r.getEspecie().getNombre())
                .build();
    }
}