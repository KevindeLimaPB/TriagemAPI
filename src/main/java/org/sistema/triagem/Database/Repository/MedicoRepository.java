package org.sistema.triagem.Database.Repository;

import org.sistema.triagem.Database.Model.FuncionarioEntity;
import org.sistema.triagem.Database.Model.MedicoEntity;
import org.sistema.triagem.Enums.EspecialidadeMedic;
import org.sistema.triagem.dto.Records.MedicoBuscaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MedicoRepository extends JpaRepository<MedicoEntity, String> {

    Optional<MedicoEntity> findByFuncionario(FuncionarioEntity funcionario);

    @Query("SELECT m FROM MedicoEntity m JOIN m.funcionario f JOIN f.usuario u WHERE u.nome = :nome")
    Optional<MedicoEntity> findByNome(@Param("nome") String nome);

    @Query("SELECT m FROM MedicoEntity m WHERE m.crm = :crm")
    Optional<MedicoEntity> findByCrm(@Param("crm") String crm);

    @Query("""
    SELECT new org.sistema.triagem.dto.Records.MedicoBuscaDTO(
        u.nome, m.crm, m.especialidade, m.preco_consulta,
        e.cidade, e.bairro, e.rua)
    FROM MedicoEntity m
    JOIN m.funcionario f
    JOIN f.usuario u
    JOIN EnderecoFuncEntity e ON e.funcionario_id = f
    WHERE (:especialidade IS NULL OR m.especialidade = :especialidade)
      AND (:cidade IS NULL OR e.cidade = :cidade)
    """)
    List<MedicoBuscaDTO> buscar(@Param("especialidade") EspecialidadeMedic especialidade,
                                @Param("cidade") String cidade);
}