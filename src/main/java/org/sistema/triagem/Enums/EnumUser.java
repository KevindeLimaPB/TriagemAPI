package org.sistema.triagem.Enums;

import lombok.Getter;

@Getter
public enum EnumUser {
    ADMIN("admin"),
    CLIENTE("cliente"),
    FUNCIONARIO("funcionario");

    private String role;

    EnumUser(String role) {
        this.role = role;
    }
}
