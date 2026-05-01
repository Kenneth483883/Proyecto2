package com.veterinaria.clinica.controller;

import com.veterinaria.clinica.dto.ConsultaDto;
import com.veterinaria.clinica.service.ConsultaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @GetMapping
    public ResponseEntity<List<ConsultaDto>> getAll() {
        return ResponseEntity.ok(consultaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(consultaService.findById(id));
    }

    @GetMapping("/mascota/{mascotaId}")
    public ResponseEntity<List<ConsultaDto>> getByMascota(@PathVariable Long mascotaId) {
        return ResponseEntity.ok(consultaService.findByMascotaId(mascotaId));
    }

    @GetMapping("/veterinario/{veterinarioId}")
    public ResponseEntity<List<ConsultaDto>> getByVeterinario(@PathVariable Long veterinarioId) {
        return ResponseEntity.ok(consultaService.findByVeterinarioId(veterinarioId));
    }

    @PostMapping
    public ResponseEntity<ConsultaDto> create(@RequestBody ConsultaDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaDto> update(@PathVariable Long id, @RequestBody ConsultaDto dto) {
        return ResponseEntity.ok(consultaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        consultaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}