package org.sistema.triagem.Database.Model;

import jakarta.persistence.*;
import lombok.*;
import org.sistema.triagem.Enums.EspecialidadeMedic;
import org.sistema.triagem.Enums.StatusMedic;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicoEntity {

    @Id
    private String crm;

    @Enumerated(EnumType.STRING)
    private EspecialidadeMedic especialidade;

    @Enumerated(EnumType.STRING)
    private StatusMedic status;

    @Column
    private BigDecimal preco_consulta;

    @Column
    private BigDecimal tele_consulta;



    @ManyToOne
    @JoinColumn(name = "funcionario_matricula")
    private FuncionarioEntity funcionario;

}
