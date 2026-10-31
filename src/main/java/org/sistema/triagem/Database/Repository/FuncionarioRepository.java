package org.sistema.triagem.Database.Repository;

import org.sistema.triagem.Database.Model.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Integer> {
}
