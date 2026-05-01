package com.veterinaria.clinica.dto;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MedicamentoDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private String unidadDosis;
}