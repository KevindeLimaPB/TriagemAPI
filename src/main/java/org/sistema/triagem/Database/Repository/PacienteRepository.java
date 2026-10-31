package org.sistema.triagem.Database.Repository;

import org.sistema.triagem.Database.Model.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Integer> {
}
