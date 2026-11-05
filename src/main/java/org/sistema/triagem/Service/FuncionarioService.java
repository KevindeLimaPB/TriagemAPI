package org.sistema.triagem.Service;

import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.FuncionarioEntity;
import org.sistema.triagem.Database.Model.UsuarioEntity;
import org.sistema.triagem.Database.Repository.FuncionarioRepository;
import org.sistema.triagem.Database.Repository.UsuarioRepository;
import org.sistema.triagem.Exception.NotFoundException;
import org.sistema.triagem.dto.FuncionarioDTO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    private final UsuarioRepository usuarioRepository;

    public FuncionarioEntity save(FuncionarioDTO funcionarioDTO) throws NotFoundException {
        UsuarioEntity usuarioEntity = usuarioRepository.findByCPF(funcionarioDTO.getCpf())
                .orElseThrow(() -> new NotFoundException("ID não encontrada"));

        FuncionarioEntity funcionarioEntity = FuncionarioEntity.builder()
                .nome(funcionarioDTO.getNome())
                .idade(funcionarioDTO.getIdade())
                .profissao(funcionarioDTO.getProfissao())
                .dataNascimento(funcionarioDTO.getData_hora())
                .usuario(usuarioEntity)
                .build();

        return  funcionarioRepository.save(funcionarioEntity);
    }

}
