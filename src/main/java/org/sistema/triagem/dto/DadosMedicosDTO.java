package org.sistema.triagem.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class DadosMedicosDTO {

    private String nome;
    private String cpf;
    private String matricula;
    private String crm;
    private String especialidade;
    private String email;
    private BigDecimal precoConsulta;
    private String senha;

}
