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

    @NotNull(message = "A especialidade da consulta é obrigatória.")
    private EspecialidadeConsulta especialidadeConsulta;

    @NotBlank(message = "O médico é obrigatório.")
    private String medico;

    @NotNull(message = "O ID do paciente é obrigatório.")
    private Integer paciente;

    @NotNull(message = "A data e hora da consulta são obrigatórias.")
    private LocalDateTime data_hora;
}