package org.sistema.triagem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sistema.triagem.Database.Model.FuncionarioEntity;
import org.sistema.triagem.Enums.EspecialidadeMedic;
import org.sistema.triagem.Enums.Status;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MedicoDTO {

    @NotNull
    private EspecialidadeMedic especialidade;

    @NotNull
    private Status status;

    @NotNull
    private Set<FuncionarioEntity> funcionario;

}
