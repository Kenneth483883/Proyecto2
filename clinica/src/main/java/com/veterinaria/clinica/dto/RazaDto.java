package com.veterinaria.clinica.dto;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RazaDto {
    private Long id;
    private String nombre;
    private Long especieId;
    private String especieNombre;
}