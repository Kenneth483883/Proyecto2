package com.veterinaria.clinica.dto;

import com.veterinaria.clinica.models.Consulta;
import com.veterinaria.clinica.models.Medicamento;
import lombok.*;

@Getter @Setter @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ConsultaMedicamentoDto {
    private Consulta consulta;
    private Medicamento medicamento;
    private int duracionDias;
}