package org.sistema.triagem.Service;

import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.EnderecoFuncEntity;
import org.sistema.triagem.Database.Model.FuncionarioEntity;
import org.sistema.triagem.Database.Repository.EnderecoFuncRepository;
import org.sistema.triagem.Database.Repository.FuncionarioRepository;
import org.sistema.triagem.Exception.NotFoundException;
import org.sistema.triagem.dto.EnderecoFuncDTO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoFuncService {

    private final EnderecoFuncRepository enderecoFuncRepository;

    private final FuncionarioRepository funcionarioRepository;

    public EnderecoFuncEntity save(EnderecoFuncDTO enderecoDTO) throws NotFoundException {
        FuncionarioEntity funcionarioEntity = funcionarioRepository.findById(enderecoDTO.getFuncionario())
                .orElseThrow(() -> new NotFoundException("Matrícula não encontrada"));

        EnderecoFuncEntity enderecoEntity = EnderecoFuncEntity.builder()
                .estado(enderecoDTO.getEstado())
                .cidade(enderecoDTO.getCidade())
                .rua(enderecoDTO.getRua())
                .cep(enderecoDTO.getCep())
                .funcionario_id(funcionarioEntity)
                .build();

        return enderecoFuncRepository.save(enderecoEntity);
    }
}
