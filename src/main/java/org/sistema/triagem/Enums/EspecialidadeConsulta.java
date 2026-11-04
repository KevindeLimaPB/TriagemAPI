package org.sistema.triagem.Enums;

import lombok.Getter;

@Getter
public enum EspecialidadeConsulta {
    CARDIOLOGIA("cardiologia"),
    CLINICA_GERAL("clínica geral"),
    PEDIATRA("pediatra"),
    DERMATOLOGIA("dermantologia"),
    ORTOPEDIA("ortopedia"),
    NEUROLOGIA("neurologia"),
    GINECOLOGIA("ginecologia"),
    OFTALMOLOGIA("oftalmologia"),
    PSIQUIATRIA("psiquiatria"),
    ENDOCRINOLOGIA("endocrinologia");

    private String especialidade;

    EspecialidadeConsulta(String especialidade){
        this.especialidade = especialidade;
    }
}
