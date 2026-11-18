package org.sistema.triagem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.sistema.triagem.Enums.Profissao;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FuncionarioDTO {

    @NotBlank
    private String matricula;

    @NotNull
    private String usuario;


    private Profissao profissao;

    @NotNull
    private Integer idade;

    @NotNull
    private LocalDate data_hora;

}
