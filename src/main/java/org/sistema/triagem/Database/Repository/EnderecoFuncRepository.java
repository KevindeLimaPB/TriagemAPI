package org.sistema.triagem.Database.Repository;

import org.sistema.triagem.Database.Model.EnderecoFuncEntity;
import org.sistema.triagem.Database.Model.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnderecoFuncRepository extends JpaRepository<EnderecoFuncEntity, Integer> {

    Optional<EnderecoFuncEntity> findByFuncionario_id(FuncionarioEntity funcionario);
}
