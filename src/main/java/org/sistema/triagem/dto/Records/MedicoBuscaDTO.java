package org.sistema.triagem.dto.Records;

import org.sistema.triagem.Enums.EspecialidadeConsulta;

import java.math.BigDecimal;

public record MedicoBuscaDTO(
        String nome,
        String crm,
        EspecialidadeConsulta especialidade,
        BigDecimal precoConsulta,
        String cidade,
        String bairro,
        String rua
) {}