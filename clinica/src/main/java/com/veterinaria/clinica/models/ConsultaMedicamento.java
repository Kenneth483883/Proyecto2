package com.veterinaria.clinica.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "consulta_medicamento")
public class ConsultaMedicamento {

    @EmbeddedId
    private ConsultaMedicamentoId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("consultaId")
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("medicamentoId")
    @JoinColumn(name = "medicamento_id")
    private Medicamento medicamento;

    private String dosis;
    private int duracionDias;
}