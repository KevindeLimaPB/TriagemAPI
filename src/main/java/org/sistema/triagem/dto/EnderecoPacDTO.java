package org.sistema.triagem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class EnderecoPacDTO {

    @NotBlank
    private String estado;

    @NotBlank
    private String cidade;

    @NotBlank
    private String rua;

    @NotBlank
    private String cep;

    @NotNull
    private Integer paciente;

    @NotNull
    private Integer numero;

}
