package org.sistema.triagem.Database.Repository;

import org.sistema.triagem.Database.Model.MedicoEntity;
import org.sistema.triagem.Enums.EspecialidadeConsulta;
import org.sistema.triagem.dto.Records.MedicoBuscaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicoRepository extends JpaRepository<MedicoEntity, String> {

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
    List<MedicoBuscaDTO> buscar(@Param("especialidade") EspecialidadeConsulta especialidade,
                                @Param("cidade") String cidade);
}
