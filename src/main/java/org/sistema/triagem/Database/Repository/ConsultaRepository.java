package org.sistema.triagem.Database.Repository;

import org.sistema.triagem.Database.Model.ConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Integer> {
    List<ConsultaEntity> findByPacienteId(Integer pacienteId);
}
