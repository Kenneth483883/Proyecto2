package com.veterinaria.clinica.service.impl;

import com.veterinaria.clinica.dto.VeterinarioDto;
import com.veterinaria.clinica.models.Veterinario;
import com.veterinaria.clinica.repository.VeterinarioRepository;
import com.veterinaria.clinica.service.VeterinarioService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VeterinarioServiceImpl implements VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;

    public VeterinarioServiceImpl(VeterinarioRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }

    @Override
    public List<VeterinarioDto> findAll() {
        return veterinarioRepository.findAll()
                .stream()
                .map(VeterinarioServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public VeterinarioDto findById(Long id) {
        Veterinario veterinario = veterinarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado con id: " + id));
        return mapToDto(veterinario);
    }

    @Override
    public VeterinarioDto save(VeterinarioDto dto) {
        return mapToDto(veterinarioRepository.save(mapToEntity(dto)));
    }

    @Override
    public VeterinarioDto update(Long id, VeterinarioDto dto) {
        Veterinario veterinario = veterinarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado con id: " + id));
        veterinario.setNombre(dto.getNombre());
        veterinario.setPrimerApellido(dto.getPrimerApellido());
        veterinario.setEspecialidad(dto.getEspecialidad());
        veterinario.setTelefono(dto.getTelefono());
        return mapToDto(veterinarioRepository.save(veterinario));
    }

    @Override
    public void delete(Long id) {
        veterinarioRepository.deleteById(id);
    }

    public static VeterinarioDto mapToDto(Veterinario v) {
        return VeterinarioDto.builder()
                .id(v.getId())
                .nombre(v.getNombre())
                .primerApellido(v.getPrimerApellido())
                .especialidad(v.getEspecialidad())
                .telefono(v.getTelefono())
                .build();
    }

    public static Veterinario mapToEntity(VeterinarioDto dto) {
        return Veterinario.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .primerApellido(dto.getPrimerApellido())
                .especialidad(dto.getEspecialidad())
                .telefono(dto.getTelefono())
                .build();
    }
}