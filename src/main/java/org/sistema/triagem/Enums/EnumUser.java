package org.sistema.triagem.Enums;

import lombok.Getter;

@Getter
public enum EnumUser {
    ADMIN("admin"),
    PACIENTE("paciente"),
    FUNCIONARIO("funcionario");

    private String role;

    EnumUser(String role) {
    }
}
