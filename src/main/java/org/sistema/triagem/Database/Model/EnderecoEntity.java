package org.sistema.triagem.Database.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Endereco")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String estado;

    @Column
    private String cidade;

    @Column
    private String rua;

    @Column
    private String cep;

    @Column
    private Integer numero;
}
