package org.sistema.triagem.Service;

import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.UsuarioEntity;
import org.sistema.triagem.Database.Repository.UsuarioRepository;
import org.sistema.triagem.Exception.Conflict;
import org.sistema.triagem.Exception.NotFoundException;
import org.sistema.triagem.dto.UsuarioDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UsuarioEntity> findAll(){
        return usuarioRepository.findAll();
    }

    public String findByNome(String cpf) throws Exception {
        UsuarioEntity usuarioEntity = usuarioRepository.findByCPF(cpf)
                .orElseThrow(() -> new NotFoundException("CPF não encontrado."));

        return usuarioEntity.getNome();
    }

    public UsuarioEntity save(UsuarioDTO usuarioDTO) throws Conflict {
        if (this.usuarioRepository.existsByCpf(usuarioDTO.getCpf())) {
            throw new Conflict("CPF já cadastrado.");
        }

        // O e-mail/login deve ser salvo em texto limpo, sem passwordEncoder!
        String login = usuarioDTO.getLogin();
        if (login != null && (login.isEmpty() || login.isBlank())) {
            login = null;
        }

        UsuarioEntity usuario = UsuarioEntity.builder()
                .nome(usuarioDTO.getNome())
                .cpf(usuarioDTO.getCpf())
                .login(login) // <-- Salva o e-mail normalmente
                .senha(passwordEncoder.encode(usuarioDTO.getSenha())) // <-- Apenas a senha é codificada
                .enumUser(usuarioDTO.getRole())
                .build();

        return usuarioRepository.save(usuario);
    }

    public void updatePassword(UsuarioDTO usuarioDTO) throws NotFoundException {
        UsuarioEntity usuarioEntity = usuarioRepository.findByEmail(usuarioDTO.getLogin())
                .orElseThrow(() -> new NotFoundException("Email não encontrado"));

        usuarioEntity.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));

        usuarioRepository.save(usuarioEntity);
    }
}