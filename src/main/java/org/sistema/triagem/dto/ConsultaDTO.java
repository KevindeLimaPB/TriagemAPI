package org.sistema.triagem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sistema.triagem.Enums.EspecialidadeConsulta;
import org.sistema.triagem.Enums.Planos_Saude;
import org.sistema.triagem.Enums.Status;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ConsultaDTO {

    @NotNull(message = "A especialidade da consulta é obrigatória.")
    private EspecialidadeConsulta especialidadeConsulta;

    private Planos_Saude planosSaude;

    private Status status;

    @NotBlank(message = "O médico é obrigatório.")
    private String medico;

    @NotBlank(message = "O CPF do paciente é obrigatório.")
    private String pacienteCpf;

    @NotNull(message = "A data e hora da consulta são obrigatórias.")
    private LocalDateTime data_hora;
}