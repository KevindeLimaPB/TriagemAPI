package org.sistema.triagem.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sistema.triagem.Enums.EnumUser;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    @NotBlank
    private String nome;

    private String login;

    @NotBlank(message = "A senha não pode ser vazia.")
    private String senha;

    @NotBlank
    @Pattern( regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "Error Formato deve ser 000.000.000-00")
    private String cpf;


    private EnumUser role;

}
