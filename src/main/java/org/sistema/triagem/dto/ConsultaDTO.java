package org.sistema.triagem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sistema.triagem.Enums.EspecialidadeConsulta;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ConsultaDTO {

    @NotNull
    private EspecialidadeConsulta especialidadeConsulta;

    @NotNull
    private Integer medico;

    @NotNull
    private Integer paciente;

    @NotBlank
    private LocalDateTime data_hora;
}


