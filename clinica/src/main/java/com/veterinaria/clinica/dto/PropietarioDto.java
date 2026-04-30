package com.veterinaria.clinica.dto;

import lombok.*;

@Getter @Setter @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PropietarioDto {
    private Long id;
    private String nombreCompleto;
    private String telefono;
    private String email;
}