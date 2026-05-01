package com.veterinaria.clinica.service.impl;

import com.veterinaria.clinica.dto.ConsultaDto;
import com.veterinaria.clinica.models.Consulta;
import com.veterinaria.clinica.models.Mascota;
import com.veterinaria.clinica.models.Veterinario;
import com.veterinaria.clinica.repository.ConsultaRepository;
import com.veterinaria.clinica.repository.MascotaRepository;
import com.veterinaria.clinica.repository.VeterinarioRepository;
import com.veterinaria.clinica.service.ConsultaService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final MascotaRepository mascotaRepository;
    private final VeterinarioRepository veterinarioRepository;

    public ConsultaServiceImpl(ConsultaRepository consultaRepository,
                               MascotaRepository mascotaRepository,
                               VeterinarioRepository veterinarioRepository) {
        this.consultaRepository = consultaRepository;
        this.mascotaRepository = mascotaRepository;
        this.veterinarioRepository = veterinarioRepository;
    }

    @Override
    public List<ConsultaDto> findAll() {
        return consultaRepository.findAll()
                .stream()
                .map(ConsultaServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ConsultaDto findById(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta no encontrada con id: " + id));
        return mapToDto(consulta);
    }

    @Override
    public List<ConsultaDto> findByMascotaId(Long mascotaId) {
        return consultaRepository.findByMascotaId(mascotaId)
                .stream()
                .map(ConsultaServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConsultaDto> findByVeterinarioId(Long veterinarioId) {
        return consultaRepository.findByVeterinarioId(veterinarioId)
                .stream()
                .map(ConsultaServiceImpl::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ConsultaDto save(ConsultaDto dto) {
        Mascota mascota = mascotaRepository.findById(dto.getMascotaId())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con id: " + dto.getMascotaId()));
        Veterinario veterinario = veterinarioRepository.findById(dto.getVeterinarioId())
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado con id: " + dto.getVeterinarioId()));
        Consulta consulta = Consulta.builder()
                .fechaConsulta(dto.getFechaConsulta())
                .motivo(dto.getMotivo())
                .diagnostico(dto.getDiagnostico())
                .mascota(mascota)
                .veterinario(veterinario)
                .build();
        return mapToDto(consultaRepository.save(consulta));
    }

    @Override
    public ConsultaDto update(Long id, ConsultaDto dto) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta no encontrada con id: " + id));
        Mascota mascota = mascotaRepository.findById(dto.getMascotaId())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con id: " + dto.getMascotaId()));
        Veterinario veterinario = veterinarioRepository.findById(dto.getVeterinarioId())
                .orElseThrow(() -> new RuntimeException("Veterinario no encontrado con id: " + dto.getVeterinarioId()));
        consulta.setFechaConsulta(dto.getFechaConsulta());
        consulta.setMotivo(dto.getMotivo());
        consulta.setDiagnostico(dto.getDiagnostico());
        consulta.setMascota(mascota);
        consulta.setVeterinario(veterinario);
        return mapToDto(consultaRepository.save(consulta));
    }

    @Override
    public void delete(Long id) {
        consultaRepository.deleteById(id);
    }

    public static ConsultaDto mapToDto(Consulta c) {
        return ConsultaDto.builder()
                .id(c.getId())
                .fechaConsulta(c.getFechaConsulta())
                .motivo(c.getMotivo())
                .diagnostico(c.getDiagnostico())
                .mascotaId(c.getMascota().getId())
                .mascotaNombre(c.getMascota().getNombre())
                .veterinarioId(c.getVeterinario().getId())
                .veterinarioNombre(c.getVeterinario().getNombre())
                .build();
    }
}