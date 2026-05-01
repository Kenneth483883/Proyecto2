package com.veterinaria.clinica.service.impl;

import com.veterinaria.clinica.dto.PropietarioDto;
import com.veterinaria.clinica.models.Propietario;
import com.veterinaria.clinica.repository.PropietarioRepository;
import com.veterinaria.clinica.service.PropietarioService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropietarioServiceImpl implements PropietarioService {

    private final PropietarioRepository propietarioRepository;

    public PropietarioServiceImpl(PropietarioRepository propietarioRepository) {
        this.propietarioRepository = propietarioRepository;
    }

    @Override
    public List<PropietarioDto> findAll() {
        return propietarioRepository.findAll()
                .stream()
                .map(PropietarioServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PropietarioDto findById(Long id) {
        Propietario propietario = propietarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Propietario no encontrado con id: " + id));
        return mapToDto(propietario);
    }

    @Override
    public PropietarioDto save(PropietarioDto dto) {
        return mapToDto(propietarioRepository.save(mapToEntity(dto)));
    }

    @Override
    public PropietarioDto update(Long id, PropietarioDto dto) {
        Propietario propietario = propietarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Propietario no encontrado con id: " + id));
        propietario.setNombre(dto.getNombre());
        propietario.setPrimerApellido(dto.getPrimerApellido());
        propietario.setSegundoApellido(dto.getSegundoApellido());
        propietario.setTelefono(dto.getTelefono());
        propietario.setEmail(dto.getEmail());
        return mapToDto(propietarioRepository.save(propietario));
    }

    @Override
    public void delete(Long id) {
        propietarioRepository.deleteById(id);
    }

    public static PropietarioDto mapToDto(Propietario p) {
        return PropietarioDto.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .primerApellido(p.getPrimerApellido())
                .segundoApellido(p.getSegundoApellido())
                .telefono(p.getTelefono())
                .email(p.getEmail())
                .build();
    }

    public static Propietario mapToEntity(PropietarioDto dto) {
        return Propietario.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .primerApellido(dto.getPrimerApellido())
                .segundoApellido(dto.getSegundoApellido())
                .telefono(dto.getTelefono())
                .email(dto.getEmail())
                .build();
    }
}