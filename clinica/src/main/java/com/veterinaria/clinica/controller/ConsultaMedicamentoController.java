package com.veterinaria.clinica.controller;

import com.veterinaria.clinica.dto.ConsultaMedicamentoDto;
import com.veterinaria.clinica.service.ConsultaMedicamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/consulta-medicamentos")
public class ConsultaMedicamentoController {

    private final ConsultaMedicamentoService consultaMedicamentoService;

    public ConsultaMedicamentoController(ConsultaMedicamentoService consultaMedicamentoService) {
        this.consultaMedicamentoService = consultaMedicamentoService;
    }

    @GetMapping
    public ResponseEntity<List<ConsultaMedicamentoDto>> getAll() {
        return ResponseEntity.ok(consultaMedicamentoService.findAll());
    }

    @GetMapping("/consulta/{consultaId}")
    public ResponseEntity<List<ConsultaMedicamentoDto>> getByConsulta(@PathVariable Long consultaId) {
        return ResponseEntity.ok(consultaMedicamentoService.findByConsultaId(consultaId));
    }

    @PostMapping
    public ResponseEntity<ConsultaMedicamentoDto> create(@RequestBody ConsultaMedicamentoDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaMedicamentoService.save(dto));
    }

    @DeleteMapping("/{consultaId}/{medicamentoId}")
    public ResponseEntity<Void> delete(@PathVariable Long consultaId, @PathVariable Long medicamentoId) {
        consultaMedicamentoService.delete(consultaId, medicamentoId);
        return ResponseEntity.noContent().build();
    }
}