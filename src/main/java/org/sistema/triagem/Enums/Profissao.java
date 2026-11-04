package org.sistema.triagem.Enums;

import lombok.Getter;

@Getter
public enum Profissao {
    MEDICO("medico"),
    SUPORTE("Suporte"),
    RECEPCIONISTA("Recepcionista");

    private String profissao;

    Profissao(String profissao){
        this.profissao = profissao;
    }
}
