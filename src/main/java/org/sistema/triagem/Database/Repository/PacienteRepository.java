package org.sistema.triagem.Database.Repository;

import org.sistema.triagem.Database.Model.PacienteEntity;
import org.sistema.triagem.Database.Model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Integer> {


    @Query("SELECT p FROM PacienteEntity p WHERE p.usuario = :cpf")
    Optional<PacienteEntity> findByUsuarioCPF(@Param("cpf") String cpf);

    Optional<PacienteEntity> findByUsuario(UsuarioEntity usuarioEntity);

}
