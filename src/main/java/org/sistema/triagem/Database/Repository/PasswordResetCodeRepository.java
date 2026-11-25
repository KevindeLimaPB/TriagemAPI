package org.sistema.triagem.Database.Repository;

import org.sistema.triagem.Database.Model.PasswordResetCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetCodeRepository extends JpaRepository<PasswordResetCodeEntity, Long> {
    Optional<PasswordResetCodeEntity> findTopByEmailAndUsedFalseOrderByCreatedAtDesc(String email);
    Optional<PasswordResetCodeEntity> findByResetTokenHashAndUsedFalse(String resetTokenHash);
    void deleteByEmail(String email);   // invalida códigos antigos
}