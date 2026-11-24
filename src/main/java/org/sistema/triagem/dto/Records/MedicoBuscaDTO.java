package org.sistema.triagem.dto.Records;

import org.sistema.triagem.Enums.EspecialidadeConsulta;
import org.sistema.triagem.Enums.EspecialidadeMedic;

import java.math.BigDecimal;

public record MedicoBuscaDTO(
        String nome,
        String crm,
        EspecialidadeMedic especialidade,
        BigDecimal precoConsulta,
        String cidade,
        String bairro,
        String rua
) {}