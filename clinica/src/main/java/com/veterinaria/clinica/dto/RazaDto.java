package com.veterinaria.clinica.dto;

import com.veterinaria.clinica.models.Especie;
import lombok.*;

@Getter @Setter @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RazaDto {
    private Long id;
    private String nombre;
    private Especie especie;
}