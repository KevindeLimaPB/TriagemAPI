package org.sistema.triagem.Service;

import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.EnderecoEntity;
import org.sistema.triagem.Database.Model.FuncionarioEntity;
import org.sistema.triagem.Database.Repository.EnderecoRepository;
import org.sistema.triagem.Database.Repository.FuncionarioRepository;
import org.sistema.triagem.Exception.NotFoundException;
import org.sistema.triagem.dto.EnderecoDTO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    private final FuncionarioRepository funcionarioRepository;

    public EnderecoEntity save(EnderecoDTO enderecoDTO, Integer id) throws NotFoundException {
        FuncionarioEntity funcionarioEntity = funcionarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ID não encontrada"));

        EnderecoEntity enderecoEntity = EnderecoEntity.builder()
                .estado(enderecoDTO.getEstado())
                .cidade(enderecoDTO.getCidade())
                .rua(enderecoDTO.getRua())
                .cep(enderecoDTO.getCep())
                .funcionario_id(funcionarioEntity)
                .build();

        return enderecoRepository.save(enderecoEntity);
    }
}
