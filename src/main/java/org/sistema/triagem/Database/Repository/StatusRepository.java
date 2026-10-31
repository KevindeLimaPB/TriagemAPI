package org.sistema.triagem.Database.Repository;

import org.sistema.triagem.Database.Model.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<StatusEntity, Integer> {
}
