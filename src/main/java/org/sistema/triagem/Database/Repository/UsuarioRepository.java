package org.sistema.triagem.Database.Repository;

import org.sistema.triagem.Database.Model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String> {


    @Query("SELECT u FROM UsuarioEntity u")
    List<UsuarioEntity> findAll();

    @Query("SELECT u FROM UsuarioEntity u WHERE u.cpf = :cpf")
    UserDetails findByCpf(@Param("cpf") String cpf);


    @Query(value = "SELECT * FROM usuario WHERE usuario.cpf = ?1", nativeQuery = true)
    Optional<UsuarioEntity> findByCPF(String cpf);

    boolean existsByCpf(String cpf);

}
