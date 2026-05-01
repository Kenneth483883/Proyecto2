package com.veterinaria.clinica.dto;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ConsultaDto {
    private Long id;
    private LocalDate fechaConsulta;
    private String motivo;
    private String diagnostico;
    private Long mascotaId;
    private String mascotaNombre;
    private Long veterinarioId;
    private String veterinarioNombre;
}