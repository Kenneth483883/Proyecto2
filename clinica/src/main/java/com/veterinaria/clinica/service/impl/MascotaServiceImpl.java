package com.veterinaria.clinica.service.impl;

import com.veterinaria.clinica.dto.MascotaDto;
import com.veterinaria.clinica.models.Mascota;
import com.veterinaria.clinica.models.Propietario;
import com.veterinaria.clinica.models.Raza;
import com.veterinaria.clinica.repository.MascotaRepository;
import com.veterinaria.clinica.repository.PropietarioRepository;
import com.veterinaria.clinica.repository.RazaRepository;
import com.veterinaria.clinica.service.MascotaService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MascotaServiceImpl implements MascotaService {

    private final MascotaRepository mascotaRepository;
    private final RazaRepository razaRepository;
    private final PropietarioRepository propietarioRepository;

    public MascotaServiceImpl(MascotaRepository mascotaRepository,
                              RazaRepository razaRepository,
                              PropietarioRepository propietarioRepository) {
        this.mascotaRepository = mascotaRepository;
        this.razaRepository = razaRepository;
        this.propietarioRepository = propietarioRepository;
    }

    @Override
    public List<MascotaDto> findAll() {
        return mascotaRepository.findAll()
                .stream()
                .map(MascotaServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public MascotaDto findById(Long id) {
        Mascota mascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con id: " + id));
        return mapToDto(mascota);
    }

    @Override
    public List<MascotaDto> findByPropietarioId(Long propietarioId) {
        return mascotaRepository.findByPropietarioId(propietarioId)
                .stream()
                .map(MascotaServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public MascotaDto save(MascotaDto dto) {
        Raza raza = razaRepository.findById(dto.getRazaId())
                .orElseThrow(() -> new RuntimeException("Raza no encontrada con id: " + dto.getRazaId()));
        Propietario propietario = propietarioRepository.findById(dto.getPropietarioId())
                .orElseThrow(() -> new RuntimeException("Propietario no encontrado con id: " + dto.getPropietarioId()));
        Mascota mascota = Mascota.builder()
                .nombre(dto.getNombre())
                .fechaNacimiento(dto.getFechaNacimiento())
                .sexo(dto.getSexo())
                .raza(raza)
                .propietario(propietario)
                .build();
        return mapToDto(mascotaRepository.save(mascota));
    }

    @Override
    public MascotaDto update(Long id, MascotaDto dto) {
        Mascota mascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con id: " + id));
        Raza raza = razaRepository.findById(dto.getRazaId())
                .orElseThrow(() -> new RuntimeException("Raza no encontrada con id: " + dto.getRazaId()));
        Propietario propietario = propietarioRepository.findById(dto.getPropietarioId())
                .orElseThrow(() -> new RuntimeException("Propietario no encontrado con id: " + dto.getPropietarioId()));
        mascota.setNombre(dto.getNombre());
        mascota.setFechaNacimiento(dto.getFechaNacimiento());
        mascota.setSexo(dto.getSexo());
        mascota.setRaza(raza);
        mascota.setPropietario(propietario);
        return mapToDto(mascotaRepository.save(mascota));
    }

    @Override
    public void delete(Long id) {
        mascotaRepository.deleteById(id);
    }

    public static MascotaDto mapToDto(Mascota m) {
        return MascotaDto.builder()
                .id(m.getId())
                .nombre(m.getNombre())
                .fechaNacimiento(m.getFechaNacimiento())
                .sexo(m.getSexo())
                .razaId(m.getRaza().getId())
                .razaNombre(m.getRaza().getNombre())
                .propietarioId(m.getPropietario().getId())
                .propietarioNombre(m.getPropietario().getNombre())
                .build();
    }
}