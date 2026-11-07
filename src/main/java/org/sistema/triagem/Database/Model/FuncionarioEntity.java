package org.sistema.triagem.Database.Model;

import jakarta.persistence.*;
import lombok.*;
import org.sistema.triagem.Enums.Profissao;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Funcionario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuncionarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer matricula;

    @OneToMany(mappedBy = "funcionario_id")
    private List<EnderecoFuncEntity> endereco;


    @OneToOne
    @JoinColumn(name = "usuario")
    private UsuarioEntity usuario;


    @OneToMany(mappedBy = "funcionario")
    private Set<MedicoEntity> medicos;


    @Enumerated(EnumType.STRING)
    private Profissao profissao;

    @Column
    private String nome;

    @Column
    private Integer idade;

    @Column(name = "data_nasciemnto", nullable = true)
    private LocalDate dataNascimento;
}
