package org.sistema.triagem.Enums;

public enum SexoBiologico {

    MASCULINO("masculino"),
    FEMININO("feminino"),
    INTERSEXO("interSexo"),
    NOTInformad("NotInformad");

    private String sexo;

    SexoBiologico(String sexo){
        this.sexo=sexo;
    }
}
