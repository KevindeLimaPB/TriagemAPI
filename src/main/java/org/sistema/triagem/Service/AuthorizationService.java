package org.sistema.triagem.Service;

import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Repository.FuncionarioRepository;
import org.sistema.triagem.Database.Repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final FuncionarioRepository funcionarioRepository; // Injetamos o repositório de funcionários

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        UserDetails usuario = usuarioRepository.findByCpf(username);
        if (usuario != null) {
            return usuario;
        }

        UserDetails funcionario = funcionarioRepository.findByMatricula(username);
        if (funcionario != null) {
            return funcionario;
        }

        throw new UsernameNotFoundException("Usuário não encontrado com o identificador: " + username);
    }
}