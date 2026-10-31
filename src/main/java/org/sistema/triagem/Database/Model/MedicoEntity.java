package org.sistema.triagem.Database.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sistema.triagem.Enums.EspecialidadeMedic;
import org.sistema.triagem.Enums.StatusMedic;

import java.util.Set;

@Entity
@Table(name = "medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicoEntity {

    @Id
    private String crm;

    @Enumerated(EnumType.STRING)
    private EspecialidadeMedic especialidade;

    @Enumerated(EnumType.STRING)
    private StatusMedic status;

    @OneToMany
    @JoinColumn(name = "funcionario_medic")
    private Set<FuncionarioEntity> funcionario;

}
