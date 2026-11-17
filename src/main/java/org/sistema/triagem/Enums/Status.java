package org.sistema.triagem.Enums;

import lombok.Getter;

@Getter
public enum Status {
    CONCLUIDA("concluída"),
    CANCELADA("cancelada"),
    EM_AGUARDO("Em aguardo");

    private String status;

    Status(String status){
        this.status = status;
    }
}
