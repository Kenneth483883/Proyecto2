package com.veterinaria.clinica.controller;

import com.veterinaria.clinica.dto.MedicamentoDto;
import com.veterinaria.clinica.service.MedicamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/medicamentos")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    @GetMapping
    public ResponseEntity<List<MedicamentoDto>> getAll() {
        return ResponseEntity.ok(medicamentoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(medicamentoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MedicamentoDto> create(@RequestBody MedicamentoDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicamentoService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicamentoDto> update(@PathVariable Long id, @RequestBody MedicamentoDto dto) {
        return ResponseEntity.ok(medicamentoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}