package org.sistema.triagem.Database.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.sistema.triagem.Enums.EspecialidadeConsulta;
import org.sistema.triagem.Enums.Planos_Saude;
import org.sistema.triagem.Enums.Status;

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

    @Enumerated(EnumType.STRING)
    private Planos_Saude planosSaude;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.EM_AGUARDO;


    @Column
    private LocalDateTime dia_hora;

    @ManyToOne
    @JsonIgnoreProperties({"funcionario"})
    @JoinColumn(name = "medico")
    private MedicoEntity medico;

    @ManyToOne
    @JoinColumn(name = "paciente")
    private PacienteEntity paciente;


}
