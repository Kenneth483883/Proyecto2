package com.veterinaria.clinica.controller;

import com.veterinaria.clinica.dto.PropietarioDto;
import com.veterinaria.clinica.service.PropietarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/propietarios")
public class PropietarioController {

    private final PropietarioService propietarioService;

    public PropietarioController(PropietarioService propietarioService) {
        this.propietarioService = propietarioService;
    }

    @GetMapping
    public ResponseEntity<List<PropietarioDto>> getAll() {
        return ResponseEntity.ok(propietarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropietarioDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(propietarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PropietarioDto> create(@RequestBody PropietarioDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propietarioService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropietarioDto> update(@PathVariable Long id, @RequestBody PropietarioDto dto) {
        return ResponseEntity.ok(propietarioService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        propietarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}