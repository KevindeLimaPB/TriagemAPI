package org.sistema.triagem.Database.Repository;

import org.sistema.triagem.Database.Model.FuncionarioEntity;
import org.sistema.triagem.Database.Model.Telefone_FuncEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Telefone_FuncRepository extends JpaRepository<Telefone_FuncEntity, Integer> {
}
