package com.veterinaria.clinica.dto;

import lombok.*;

@Getter @Setter @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class EspecieDto {
    private Long id;
    private String nombre;
}

