package org.sistema.triagem.Enums;

import lombok.Getter;

@Getter
public enum StatusMedic {
    ATIVO("ativo"),
    INATIVO("inativo");

    private String status;

    StatusMedic(String status){
        this.status = status;
    }
}
