package com.veterinaria.clinica.controller;

import com.veterinaria.clinica.dto.VeterinarioDto;
import com.veterinaria.clinica.service.VeterinarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/veterinarios")
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    public VeterinarioController(VeterinarioService veterinarioService) {
        this.veterinarioService = veterinarioService;
    }

    @GetMapping
    public ResponseEntity<List<VeterinarioDto>> getAll() {
        return ResponseEntity.ok(veterinarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeterinarioDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(veterinarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<VeterinarioDto> create(@RequestBody VeterinarioDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(veterinarioService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeterinarioDto> update(@PathVariable Long id, @RequestBody VeterinarioDto dto) {
        return ResponseEntity.ok(veterinarioService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        veterinarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}