package org.sistema.triagem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.sistema.triagem.Enums.EnumUser;

public class UsuarioDTO {

    @NotBlank(message = "Não pode ser vazio.")
    @Size(min = 10, max = 30, message = "Tem que possui no mínimo 10 caracteres.")
    private String login;

    @NotBlank(message = "A senha não pode ser vazia.")
    private String senha;

    @NotBlank
    @Pattern( regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}\\d$", message = "Error Formato deve ser 000.000.000-00")
    private String cpf;


    private EnumUser enumUser;

}
