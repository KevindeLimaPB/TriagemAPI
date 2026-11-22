package org.sistema.triagem.dto;

import lombok.Getter;
import org.sistema.triagem.Enums.EnumUser;
import org.sistema.triagem.Enums.EspecialidadeMedic;

import java.math.BigDecimal;

@Getter
public class DadosMedicosDTO {

    private String nome;
    private String cpf;
    private String senha;
    private EnumUser role;

    private String matricula;

    private String crm;
    private EspecialidadeMedic especialidade;
    private String cidade;
    private String email;
    private BigDecimal precoConsulta;

}
