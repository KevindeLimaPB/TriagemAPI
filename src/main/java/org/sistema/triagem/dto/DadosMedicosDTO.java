package org.sistema.triagem.dto;

import lombok.Getter;
import lombok.Setter;
import org.sistema.triagem.Enums.EnumUser;
import org.sistema.triagem.Enums.EspecialidadeMedic;
import org.sistema.triagem.Enums.Profissao;

import java.math.BigDecimal;

@Getter
@Setter
public class DadosMedicosDTO {

    private String nome;
    private String cpf;
    private String senha;
    private EnumUser role;

    private String matricula;
    private Profissao profissao;

    private String crm;
    private EspecialidadeMedic especialidade;
    private String cidade;
    private String bairro;
    private String rua;
    private String email;
    private BigDecimal precoConsulta;

}
