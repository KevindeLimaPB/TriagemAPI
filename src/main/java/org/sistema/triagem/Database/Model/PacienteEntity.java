package org.sistema.triagem.Database.Model;

import jakarta.persistence.*;
import lombok.*;
import org.sistema.triagem.Enums.SexoBiologico;
import org.w3c.dom.stylesheets.LinkStyle;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "paciente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "paciente")
    private List<EnderecoPacEntity> endereco;

    @Column
    private Integer idade;

    @Column
    private BigDecimal peso;

    @Column
    private BigDecimal altura;

    @Enumerated(EnumType.STRING)
    private SexoBiologico sexoBiologico;

    @Column(name = "data_nasciemnto", nullable = true)
    private LocalDate dataNascimento;

}
