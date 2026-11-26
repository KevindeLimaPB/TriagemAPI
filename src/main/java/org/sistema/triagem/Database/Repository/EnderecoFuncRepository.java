package org.sistema.triagem.Database.Repository;

import org.sistema.triagem.Database.Model.EnderecoFuncEntity;
import org.sistema.triagem.Database.Model.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EnderecoFuncRepository extends JpaRepository<EnderecoFuncEntity, Integer> {

    @Query("SELECT e FROM EnderecoFuncEntity e WHERE e.funcionario_id = :funcionario")
    Optional<EnderecoFuncEntity> findByFuncionario_id(@Param("funcionario") FuncionarioEntity funcionario);
}