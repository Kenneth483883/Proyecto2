package com.veterinaria.clinica.dto;

import com.veterinaria.clinica.models.Mascota;
import com.veterinaria.clinica.models.Medicamento;
import com.veterinaria.clinica.models.Veterinario;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;

@Getter @Setter @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ConsultaDto {
    private Long id;
    private LocalDate fechaConsulta;
    private Mascota mascota;
    private Veterinario veterinario;
    private Set<Medicamento> medicamentos;
}
