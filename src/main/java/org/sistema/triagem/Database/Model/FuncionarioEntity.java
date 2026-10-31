package org.sistema.triagem.Database.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sistema.triagem.Enums.Profissao;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Funcionario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer matricula;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private UsuarioEntity usuario;

    @OneToOne
    @JoinColumn(name = "endereco")
    private EnderecoEntity endereco;


    @Enumerated(EnumType.STRING)
    private Profissao profissao;

    @Column
    private String nome;

    @Column
    private Integer idade;

    @Column(name = "data_nasciemnto", nullable = true)
    private LocalDate dataNascimento;
}
