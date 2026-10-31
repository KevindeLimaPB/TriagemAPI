package org.sistema.triagem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDTO {

    @NotNull
    private String usuario;

    @NotNull
    private Integer endereco;

    @NotBlank(message = "Tem que ter um nome")
    private String nome;

    @NotNull
    private Integer idade;

    @NotBlank
    private LocalDateTime data_hora;
}
