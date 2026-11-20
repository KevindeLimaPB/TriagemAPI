package org.sistema.triagem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sistema.triagem.Enums.Generos;
import org.sistema.triagem.Enums.SexoBiologico;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDTO {

    @NotNull
    private String usuario;

    @NotNull
    private Integer idade;

    @NotNull
    private Double peso;
    @NotNull
    private Double altura;

    private SexoBiologico sexoBiologico;

    private Generos generos;

    @NotNull
    private LocalDate data_Nascimento;
}
