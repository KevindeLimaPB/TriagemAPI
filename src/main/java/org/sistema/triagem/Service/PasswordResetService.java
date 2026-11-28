package org.sistema.triagem.Service;

import org.sistema.triagem.Database.Model.PasswordResetCodeEntity;
import org.sistema.triagem.Database.Model.UsuarioEntity;
import org.sistema.triagem.Database.Repository.PasswordResetCodeRepository;
import org.sistema.triagem.Database.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class PasswordResetService {

    private static final SecureRandom RNG = new SecureRandom();

    private final PasswordResetCodeRepository repo;
    private final UsuarioRepository usuarioRepo;
    private final PasswordEncoder encoder;
    private final EmailService emailService;

    @Value("${app.reset.code-ttl-minutes:10}")
    private long codeTtlMinutes;

    @Value("${app.reset.token-ttl-minutes:15}")
    private long tokenTtlMinutes;

    @Value("${app.reset.max-attempts:5}")
    private int maxAttempts;

    @Value("${app.reset.cooldown-seconds:60}")
    private long cooldownSeconds;

    public PasswordResetService(PasswordResetCodeRepository repo,
                                UsuarioRepository usuarioRepo,
                                PasswordEncoder encoder,
                                EmailService emailService) {
        this.repo = repo;
        this.usuarioRepo = usuarioRepo;
        this.encoder = encoder;
        this.emailService = emailService;
    }

    @Transactional
    public void solicitarRecuperacao(String email, String ip) {
        String emailNorm = email.trim().toLowerCase();

        Optional<PasswordResetCodeEntity> ultimo = repo
                .findTopByEmailAndUsedFalseOrderByCreatedAtDesc(emailNorm);
        if (ultimo.isPresent()) {
            Instant limite = ultimo.get().getCreatedAt().plusSeconds(cooldownSeconds);
            if (Instant.now().isBefore(limite)) {
                throw new RuntimeException("Aguarde antes de solicitar um novo código.");
            }
        }

        Optional<UsuarioEntity> usuario = usuarioRepo.findByEmail(emailNorm);
        if (usuario.isEmpty()) {
            return; // Silencioso por segurança
        }

        repo.deleteByEmail(emailNorm);

        String codigo = String.format("%06d", RNG.nextInt(1_000_000));

        PasswordResetCodeEntity entity = new PasswordResetCodeEntity();
        entity.setEmail(emailNorm);
        entity.setCodeHash(encoder.encode(codigo));
        entity.setExpiresAt(Instant.now().plus(codeTtlMinutes, ChronoUnit.MINUTES));
        entity.setRequestIp(ip);
        repo.save(entity);

        emailService.enviarCodigoRecuperacao(emailNorm, codigo);
    }

    @Transactional
    public String validarCodigo(String email, String codigo) {
        PasswordResetCodeEntity prc = repo
                .findTopByEmailAndUsedFalseOrderByCreatedAtDesc(email.toLowerCase())
                .orElseThrow(() -> new RuntimeException("Código inválido ou expirado."));

        if (prc.getAttempts() >= maxAttempts)
            throw new RuntimeException("Muitas tentativas. Solicite um novo código.");

        if (Instant.now().isAfter(prc.getExpiresAt())) {
            prc.setUsed(true);
            repo.save(prc);
            throw new RuntimeException("Código expirado.");
        }

        if (!encoder.matches(codigo, prc.getCodeHash())) {
            prc.setAttempts(prc.getAttempts() + 1);
            repo.save(prc);
            throw new RuntimeException("Código inválido.");
        }

        String resetToken = randomToken();

        // Guarda o token diretamente em texto plano para evitar falhas de hash aleatório do BCrypt
        prc.setResetTokenHash(resetToken);
        prc.setResetTokenExpiresAt(Instant.now().plus(tokenTtlMinutes, ChronoUnit.MINUTES));
        repo.save(prc);

        return resetToken;
    }

    @Transactional
    public void redefinirSenha(String resetToken, String novaSenha) {
        if (novaSenha == null || novaSenha.length() < 6)
            throw new IllegalArgumentException("Senha muito curta.");

        PasswordResetCodeEntity prc = repo
                .findByResetTokenHashAndUsedFalse(resetToken)
                .orElseThrow(() -> new RuntimeException("Sessão expirada. Solicite um novo código."));

        if (prc.getResetTokenExpiresAt() == null
                || Instant.now().isAfter(prc.getResetTokenExpiresAt())) {
            throw new RuntimeException("Sessão expirada. Solicite um novo código.");
        }

        UsuarioEntity usuario = usuarioRepo.findByEmail(prc.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        usuario.setSenha(encoder.encode(novaSenha));
        usuarioRepo.save(usuario);

        prc.setUsed(true);
        prc.setResetTokenHash(null);
        prc.setResetTokenExpiresAt(null);
        repo.save(prc);
    }

    private String randomToken() {
        byte[] buf = new byte[32];
        RNG.nextBytes(buf);
        StringBuilder sb = new StringBuilder(64);
        for (byte b : buf) sb.append(String.format("%02x", b));
        return sb.toString();
    }
}