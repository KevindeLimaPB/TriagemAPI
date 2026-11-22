package org.sistema.triagem.Service;

import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.FuncionarioEntity;
import org.sistema.triagem.Database.Model.UsuarioEntity;
import org.sistema.triagem.Database.Repository.FuncionarioRepository;
import org.sistema.triagem.Database.Repository.UsuarioRepository;
import org.sistema.triagem.Enums.EnumUser;
import org.sistema.triagem.Exception.NotAuthorized;
import org.sistema.triagem.Exception.NotFoundException;
import org.sistema.triagem.dto.DadosMedicosDTO;
import org.sistema.triagem.dto.FuncionarioDTO;
import org.sistema.triagem.dto.UsuarioDTO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    private final UsuarioRepository usuarioRepository;

    public FuncionarioEntity save(FuncionarioDTO funcionarioDTO) throws NotFoundException, NotAuthorized {
        UsuarioEntity usuarioEntity = usuarioRepository.findByCPF(funcionarioDTO.getUsuario())
                .orElseThrow(() -> new NotFoundException("ID não encontrada"));

        if (usuarioEntity.getEnumUser() != EnumUser.FUNCIONARIO){
            throw new NotAuthorized("Você não está autorizado");
        }

        FuncionarioEntity funcionarioEntity = FuncionarioEntity.builder()
                .matricula(funcionarioDTO.getMatricula())
                .idade(funcionarioDTO.getIdade())
                .profissao(funcionarioDTO.getProfissao())
                .dataNascimento(funcionarioDTO.getData_hora())
                .usuario(usuarioEntity)
                .build();

        return  funcionarioRepository.save(funcionarioEntity);
    }

    public String obterNomePorCpf(DadosMedicosDTO dadosMedicosDTO) throws Exception {
        String identificador = dadosMedicosDTO.getCpf(); // aqui pode vir o valor que estava no token

        // Tenta buscar primeiro se existe um funcionário com essa matrícula
        FuncionarioEntity funcionario = funcionarioRepository.findByMatriculas(identificador);

        UsuarioEntity usuarioEntity;

        if (funcionario != null) {
            // Se achou pelo funcionário/matrícula, pega o usuário vinculado a ele
            usuarioEntity = funcionario.getUsuario();
        } else {
            // Se não achou por matrícula, tenta buscar direto na tabela de usuário pelo CPF
            usuarioEntity = usuarioRepository.findByCPF(identificador)
                    .orElseThrow(() -> new NotFoundException("Usuário não encontrado para o identificador: " + identificador));
        }

        return usuarioEntity.getNome();
    }

}
