package com.veterinaria.clinica.dto;

import lombok.*;

@Getter @Setter @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class VeterinarioDto {
    private Long id;
    private String nombreCompleto;
    private String especialidad;
    private String telefono;
}