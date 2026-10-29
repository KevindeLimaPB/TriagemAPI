package org.sistema.triagem.Database.Repository;

import org.sistema.triagem.Database.Model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

}
