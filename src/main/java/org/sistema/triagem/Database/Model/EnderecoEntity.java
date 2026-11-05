package org.sistema.triagem.Database.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Endereco")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private FuncionarioEntity funcionario_id;

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
