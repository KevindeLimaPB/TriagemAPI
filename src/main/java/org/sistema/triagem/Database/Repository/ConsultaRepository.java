package org.sistema.triagem.Database.Repository;

import org.sistema.triagem.Database.Model.ConsultaEntity;
import org.sistema.triagem.Database.Model.MedicoEntity;
import org.sistema.triagem.Enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Integer> {
    List<ConsultaEntity> findByPacienteUsuarioCpf(String cpf);
    @Query("SELECT COUNT(c) > 0 FROM ConsultaEntity c " +
            "WHERE c.medico = :medico AND c.dia_hora = :diaHora AND c.status <> :cancelada")
    boolean existeConflito(@Param("medico") MedicoEntity medico,
                           @Param("diaHora") LocalDateTime diaHora,
                           @Param("cancelada") Status cancelada);
}
