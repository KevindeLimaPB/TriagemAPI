package org.sistema.triagem.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sistema.triagem.Database.Model.UsuarioEntity;
import org.sistema.triagem.Database.Repository.UsuarioRepository;
import org.sistema.triagem.Enums.EnumUser;
import org.sistema.triagem.Service.UsuarioService;
import org.sistema.triagem.dto.UsuarioDTO;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioTEST {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UsuarioDTO usuarioDTO;

    @BeforeEach
    void setUp() {
        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setCpf("123.456.789-00");
        usuarioDTO.setLogin("meulogin");
        usuarioDTO.setSenha("senha123");
        usuarioDTO.setRole(EnumUser.FUNCIONARIO);
    }

    @Test
    @DisplayName("Deve salvar usuário com sucesso quando os dados forem válidos")
    void deveSalvarUsuarioComSucesso() throws Exception {
        when(usuarioRepository.existsByCpf(usuarioDTO.getCpf())).thenReturn(false);
        when(passwordEncoder.encode("senha123")).thenReturn("senhaCriptografada");
        when(passwordEncoder.encode("meulogin")).thenReturn("loginCriptografado");

        when(usuarioRepository.save(any(UsuarioEntity.class))).thenAnswer(invocation -> {
            UsuarioEntity entity = invocation.getArgument(0);
            return entity;
        });

        UsuarioEntity resultado = usuarioService.save(usuarioDTO);

        assertNotNull(resultado);
        assertEquals("123.456.789-00", resultado.getCpf());
        assertEquals("senhaCriptografada", resultado.getPassword());
        assertEquals("loginCriptografado", resultado.getLogin());

        verify(usuarioRepository, times(1)).save(any(UsuarioEntity.class));
    }

    @Test
    @DisplayName("Deve definir login como null se tiver algum espaço ou vazio")
    void deveColocaNullEmLoginVazio() throws Exception {

        usuarioDTO.setLogin(" ");
        when(usuarioRepository.existsByCpf(usuarioDTO.getCpf())).thenReturn(false);
        when(passwordEncoder.encode("senha123")).thenReturn("senhaCriptografada");

        when(usuarioRepository.save(any(UsuarioEntity.class))).thenAnswer(invocation -> {
            UsuarioEntity entity = invocation.getArgument(0);
            return entity;
        });

        UsuarioEntity usuario = usuarioService.save(usuarioDTO);

        assertNotNull(usuario);
        assertNull(usuario.getLogin(), "O login deveria ser nulo pois contém apenas espaços");
        verify(passwordEncoder, never()).encode(null);
    }


}
