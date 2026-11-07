package org.sistema.triagem.Database.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Endereco_pac")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoPacEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private PacienteEntity paciente;

    @Column
    private String estado;

    @Column
    private String cidade;

    @Column
    private String rua;

    @Column
    private String cep;

    @Column
    private Integer numero;
}
