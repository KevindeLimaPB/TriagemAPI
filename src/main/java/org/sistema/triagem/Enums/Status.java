package org.sistema.triagem.Enums;

import lombok.Getter;

@Getter
public enum Status {
    CONCLUIDA("concluída"),
    CANCELADA("cancelada"),
    MARCADA("marcada");

    private String status;

    Status(String status){
        this.status = status;
    }
}
