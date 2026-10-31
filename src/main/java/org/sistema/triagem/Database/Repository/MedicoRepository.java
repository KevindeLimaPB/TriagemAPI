package org.sistema.triagem.Database.Repository;

import org.sistema.triagem.Database.Model.MedicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<MedicoEntity, String> {
}
