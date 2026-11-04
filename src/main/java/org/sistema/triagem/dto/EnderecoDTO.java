package org.sistema.triagem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EnderecoDTO {

    @NotBlank
    private String estado;

    @NotBlank
    private String cidade;

    @NotBlank
    private String rua;

    @NotBlank
    private String cep;

    @NotNull
    private Integer funcionario;


}
