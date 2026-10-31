package org.sistema.triagem.Database.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sistema.triagem.Enums.EspecialidadeConsulta;
import java.time.LocalDateTime;

@Entity
@Table(name = "consulta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private EspecialidadeConsulta especialidade;

    @Column
    private LocalDateTime dia_hora;

    @OneToOne
    @JoinColumn(name = "medico")
    private MedicoEntity medico;

    @OneToOne
    @JoinColumn(name = "paciente")
    private PacienteEntity paciente;


}
