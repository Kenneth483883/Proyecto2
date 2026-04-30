package com.veterinaria.clinica.dto;

import com.veterinaria.clinica.models.Propietario;
import com.veterinaria.clinica.models.Raza;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter @Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MascotaDto {
    private Long id;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String sexo;
    private Propietario propietario;
    private Raza raza;
}