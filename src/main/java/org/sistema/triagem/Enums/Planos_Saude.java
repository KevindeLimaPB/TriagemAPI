package org.sistema.triagem.Enums;

import lombok.Getter;

@Getter
public enum Planos_Saude {
    UNIMED("unimed"),
    BRADESCO_SAUDE("bradesco saúde"),
    SUL_AMERICA("sul américa"),
    AMIL("amil"),
    HAPVIDA("hapvida");

    private String planos;

    Planos_Saude(String planos){
        this.planos = planos;
    }


}
