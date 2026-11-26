package org.sistema.triagem.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MedicoPerfilDTO {
    private String nome;
    private String matricula;
    private String crm;
    private String especialidade;
    private String cidade;
    private String rua;
    private BigDecimal precoConsulta;
}