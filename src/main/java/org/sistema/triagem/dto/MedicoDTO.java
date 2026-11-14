package org.sistema.triagem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sistema.triagem.Database.Model.FuncionarioEntity;
import org.sistema.triagem.Enums.EspecialidadeMedic;
import org.sistema.triagem.Enums.Status;

import java.math.BigDecimal;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MedicoDTO {

    @NotBlank
    private String crm;

    @NotNull
    private EspecialidadeMedic especialidade;

    @NotNull
    private Status status;

    @NotNull
    private BigDecimal preco_consulta;

    @NotNull
    private BigDecimal tele_consulta;


    @NotNull
    private Integer funcionario;

}
