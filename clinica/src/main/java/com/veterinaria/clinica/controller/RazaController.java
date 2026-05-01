package com.veterinaria.clinica.controller;

import com.veterinaria.clinica.dto.RazaDto;
import com.veterinaria.clinica.service.RazaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/razas")
public class RazaController {

    private final RazaService razaService;

    public RazaController(RazaService razaService) {
        this.razaService = razaService;
    }

    @GetMapping
    public ResponseEntity<List<RazaDto>> getAll() {
        return ResponseEntity.ok(razaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RazaDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(razaService.findById(id));
    }

    @GetMapping("/especie/{especieId}")
    public ResponseEntity<List<RazaDto>> getByEspecie(@PathVariable Long especieId) {
        return ResponseEntity.ok(razaService.findByEspecieId(especieId));
    }

    @PostMapping
    public ResponseEntity<RazaDto> create(@RequestBody RazaDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(razaService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RazaDto> update(@PathVariable Long id, @RequestBody RazaDto dto) {
        return ResponseEntity.ok(razaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        razaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}