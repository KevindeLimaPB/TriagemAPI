package org.sistema.triagem.Enums;

import lombok.Getter;

@Getter
public enum Tipo_Consulta {

    PRESENCIAL("presencial"),
    ONLINE("online");

    private String tipo;

    Tipo_Consulta(String tipo){
        this.tipo=tipo;
    }
}
