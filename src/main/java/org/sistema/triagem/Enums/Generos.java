package org.sistema.triagem.Enums;

import lombok.Getter;

@Getter
public enum Generos {

    CISGENERO("Cisgênero"),
    TRANSGENERO("Transgênero"),
    NAO_BINARIO("Não-binário"),
    PREFIRO_NAO_INFORMAR("Prefiro não informar");

    private final String descricao;

    Generos(String descricao) {
        this.descricao = descricao;
    }
}
