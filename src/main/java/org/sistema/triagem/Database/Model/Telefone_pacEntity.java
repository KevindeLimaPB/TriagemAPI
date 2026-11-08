package org.sistema.triagem.Database.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "telefone_pac")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Telefone_pacEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "paciente")
    private PacienteEntity paciente;
}

