package org.sistema.triagem.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Service.PasswordResetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class PasswordResetController {

    private final PasswordResetService service;

    @PostMapping("/recuperar-senha")
    public ResponseEntity<Object> recuperar(
            @Valid @RequestBody SolicitarRecuperacaoDTO dto,
            HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        service.solicitarRecuperacao(dto.email(), ip);
        return ResponseEntity.ok(Map.of("message", "Se o e-mail estiver cadastrado, enviaremos um código."));
    }

    @PostMapping("/validar-codigo")
    public ResponseEntity<Object> validar(
            @Valid @RequestBody ValidarCodigoDTO dto) {
        String token = service.validarCodigo(dto.email(), dto.codigo());
        return ResponseEntity.ok(Map.of("resetToken", token));
    }

    @PostMapping("/redefinir-senha")
    public ResponseEntity<Void> redefinir(
            @Valid @RequestBody RedefinirSenhaDTO dto) {
        service.redefinirSenha(dto.resetToken(), dto.novaSenha());
        return ResponseEntity.noContent().build();
    }

    // Records / DTOs auxiliares
    public record SolicitarRecuperacaoDTO(@NotBlank @Email String email) {}
    public record ValidarCodigoDTO(@NotBlank @Email String email, @NotBlank @Pattern(regexp = "^\\d{6}$") String codigo) {}
    public record RedefinirSenhaDTO(@NotBlank String resetToken, @NotBlank @Size(min = 6) String novaSenha) {}
}