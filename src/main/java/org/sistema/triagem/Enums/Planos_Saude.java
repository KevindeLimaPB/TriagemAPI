package org.sistema.triagem.Enums;

import lombok.Getter;

@Getter
public enum Planos_Saude {
    NOT_FOUND("Não informado"),
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
