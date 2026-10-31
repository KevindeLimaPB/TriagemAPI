package org.sistema.triagem.Database.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "paciente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private UsuarioEntity usuario;

    @OneToOne
    @JoinColumn(name = "endereco")
    private EnderecoEntity endereco;

    @Column
    private String nome;

    @Column
    private Integer idade;

    @Column(name = "data_nasciemnto", nullable = true)
    private LocalDate dataNascimento;

}
