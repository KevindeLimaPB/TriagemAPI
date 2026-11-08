package org.sistema.triagem.Database.Model;

import jakarta.persistence.*;
import lombok.*;
import org.sistema.triagem.Enums.EspecialidadeConsulta;
import java.time.LocalDateTime;

@Entity
@Table(name = "consulta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private EspecialidadeConsulta especialidade;

    @Column
    private LocalDateTime dia_hora;

    @ManyToOne
    @JoinColumn(name = "medico")
    private MedicoEntity medico;

    @ManyToOne
    @JoinColumn(name = "paciente")
    private PacienteEntity paciente;


}
