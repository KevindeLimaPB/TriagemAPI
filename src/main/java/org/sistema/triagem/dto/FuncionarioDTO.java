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

    @NotNull
    private String usuario;

    @NotNull
    private Profissao profissao;

    @NotBlank(message = "Tem que ter um nome")
    private String nome;

    @NotNull
    private Integer idade;

    @NotNull
    private LocalDate data_hora;

}
