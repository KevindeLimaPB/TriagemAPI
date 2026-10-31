package org.sistema.triagem.Database.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "telefone_pac")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

