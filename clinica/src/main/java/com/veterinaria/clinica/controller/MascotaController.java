package com.veterinaria.clinica.controller;

import com.veterinaria.clinica.dto.MascotaDto;
import com.veterinaria.clinica.service.MascotaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/mascotas")
public class MascotaController {

    private final MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @GetMapping
    public ResponseEntity<List<MascotaDto>> getAll() {
        return ResponseEntity.ok(mascotaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MascotaDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mascotaService.findById(id));
    }

    @GetMapping("/propietario/{propietarioId}")
    public ResponseEntity<List<MascotaDto>> getByPropietario(@PathVariable Long propietarioId) {
        return ResponseEntity.ok(mascotaService.findByPropietarioId(propietarioId));
    }

    @PostMapping
    public ResponseEntity<MascotaDto> create(@RequestBody MascotaDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mascotaService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MascotaDto> update(@PathVariable Long id, @RequestBody MascotaDto dto) {
        return ResponseEntity.ok(mascotaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mascotaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}