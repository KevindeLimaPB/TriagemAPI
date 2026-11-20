package org.sistema.triagem.dto;

import lombok.Getter;
import lombok.Setter;
import org.sistema.triagem.Enums.Generos;
import org.sistema.triagem.Enums.SexoBiologico;

import java.time.LocalDate;

@Getter
@Setter
public class DadosPacientesDTO {

    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private SexoBiologico sexoBiologico;
    private Generos Genero;
    private Double peso;
    private Double altura;

}
