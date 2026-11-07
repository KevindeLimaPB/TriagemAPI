package org.sistema.triagem.Database.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "telefone_func")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Telefone_FuncEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "funcionario")
    private FuncionarioEntity funcionario;
}
