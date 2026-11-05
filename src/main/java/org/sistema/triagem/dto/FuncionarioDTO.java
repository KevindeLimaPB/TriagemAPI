package org.sistema.triagem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank
    private LocalDate data_hora;

    @NotBlank
    @Pattern( regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "Error Formato deve ser 000.000.000-00")
    private String cpf;

}
