package org.sistema.triagem.Database.Repository;

import org.sistema.triagem.Database.Model.PacienteEntity;
import org.sistema.triagem.Database.Model.Telefone_pacEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface Telefone_pacRepository extends JpaRepository<Telefone_pacEntity, Integer> {

    @Query("SELECT t FROM Telefone_pacEntity t WHERE t.paciente = :paciente")
    Optional<Telefone_pacEntity> findByPacienteID(@Param("paciente") Integer paciente);

    Optional<Telefone_pacEntity> findByPaciente(PacienteEntity paciente);

}
