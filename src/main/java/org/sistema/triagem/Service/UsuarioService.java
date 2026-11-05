package org.sistema.triagem.Service;

import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.UsuarioEntity;
import org.sistema.triagem.Database.Repository.UsuarioRepository;
import org.sistema.triagem.Exception.Conflict;
import org.sistema.triagem.Exception.NotFoundException;
import org.sistema.triagem.dto.UsuarioDTO;
import org.springframework.security.authentication.AuthenticationManager;
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

    public UsuarioEntity save(UsuarioDTO usuarioDTO) throws Conflict {
        if (this.usuarioRepository.existsByCpf(usuarioDTO.getCpf())) {
            throw new Conflict("CPF inválido");
        }

        var loginOpcional = usuarioDTO.getLogin();

        if (loginOpcional.isEmpty() || loginOpcional.contains(" ")) loginOpcional = null;

        if (loginOpcional != null) loginOpcional = passwordEncoder.encode(loginOpcional);

        UsuarioEntity usuario = UsuarioEntity.builder()
                .cpf(usuarioDTO.getCpf())
                .login(loginOpcional)
                .senha(passwordEncoder.encode(usuarioDTO.getSenha()))
                .enumUser(usuarioDTO.getRole())
                .build();

        return usuarioRepository.save(usuario);
    }

    public void updatePassword(UsuarioDTO usuarioDTO) throws NotFoundException {
        UsuarioEntity usuarioEntity = usuarioRepository.findByCPF(usuarioDTO.getCpf())
                .orElseThrow(() -> new NotFoundException("CPF não encontrado"));

        UsuarioEntity usuario = UsuarioEntity.builder()
                .cpf(usuarioEntity.getCpf())
                .senha(usuarioDTO.getSenha())
                .build();

        usuarioRepository.updatePassword(usuario.getCpf(), usuario.getSenha());

    }
}
