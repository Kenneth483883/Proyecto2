package com.veterinaria.clinica.models;

import jakarta.persistence.Embeddable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class ConsultaMedicamentoId {

    private Long consultaId;
    private Long medicamentoId;
}
