package org.sistema.triagem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.FuncionarioEntity; // Importe a entidade de funcionário
import org.sistema.triagem.Database.Model.UsuarioEntity;
import org.sistema.triagem.Security.TokenService;
import org.sistema.triagem.dto.AuthenticationDTO;
import org.sistema.triagem.dto.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authenticationDTO){
        var usernamePassword = new UsernamePasswordAuthenticationToken(
                authenticationDTO.document(),
                authenticationDTO.senha());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var principal = auth.getPrincipal();
        String token;

        if (principal instanceof UsuarioEntity) {
            token = tokenService.generateToken((UsuarioEntity) principal);
        } else if (principal instanceof FuncionarioEntity) {
            token = tokenService.generateToken((FuncionarioEntity) principal);
        } else {
            throw new RuntimeException("Tipo de usuário desconhecido.");
        }

        return ResponseEntity.ok(new LoginResponse(token));
    }
}