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
public class EnderecoFuncDTO {

    @NotBlank
    private String estado;

    @NotBlank
    private String cidade;

    @NotBlank
    private String rua;

    @NotNull
    private Integer numero;

    @NotBlank
    private String cep;

    @NotNull
    private String funcionario;


}
