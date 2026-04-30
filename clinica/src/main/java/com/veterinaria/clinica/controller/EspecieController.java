package com.veterinaria.clinica.controller;

import com.veterinaria.clinica.dto.EspecieDto;
import com.veterinaria.clinica.service.EspecieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/especies")
public class EspecieController {

    private final EspecieService especieService;

    public EspecieController(EspecieService especieService) {
        this.especieService = especieService;
    }

    @GetMapping
    public ResponseEntity<List<EspecieDto>> getAll() {
        return ResponseEntity.ok(especieService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecieDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(especieService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EspecieDto> create(@RequestBody EspecieDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(especieService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecieDto> update(@PathVariable Long id, @RequestBody EspecieDto dto) {
        return ResponseEntity.ok(especieService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        especieService.delete(id);
        return ResponseEntity.noContent().build();
    }
}