package com.veterinaria.clinica.dto;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MascotaDto {
    private Long id;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String sexo;
    private Long propietarioId;
    private String propietarioNombre;
    private Long razaId;
    private String razaNombre;
}