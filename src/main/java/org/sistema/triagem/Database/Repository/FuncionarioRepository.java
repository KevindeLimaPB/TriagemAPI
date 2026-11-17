package org.sistema.triagem.Database.Repository;

import org.sistema.triagem.Database.Model.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, String> {


    @Query("SELECT f FROM FuncionarioEntity f WHERE f.matricula = :matricula")
    UserDetails findByMatricula(@Param("matricula") String matricula);

}
