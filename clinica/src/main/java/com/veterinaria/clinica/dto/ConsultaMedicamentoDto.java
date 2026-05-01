package com.veterinaria.clinica.dto;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ConsultaMedicamentoDto {
    private Long consultaId;
    private Long medicamentoId;
    private String medicamentoNombre;
    private String dosis;
    private int duracionDias;
}