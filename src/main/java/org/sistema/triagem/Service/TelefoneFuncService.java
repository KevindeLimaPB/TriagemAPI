package org.sistema.triagem.Service;

import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.FuncionarioEntity;
import org.sistema.triagem.Database.Model.Telefone_FuncEntity;
import org.sistema.triagem.Database.Repository.FuncionarioRepository;
import org.sistema.triagem.Database.Repository.Telefone_FuncRepository;
import org.sistema.triagem.Exception.NotFoundException;
import org.sistema.triagem.dto.Telefone_funcDTO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelefoneFuncService {

    private final Telefone_FuncRepository telefoneFuncRepository;
    private final FuncionarioRepository funcionarioRepository;

    public Telefone_FuncEntity save(Telefone_funcDTO telefoneFuncDTO) throws  NotFoundException {
        FuncionarioEntity funcionarioEntity = funcionarioRepository.findById(telefoneFuncDTO.getFuncionario())
                .orElseThrow(()-> new NotFoundException("Matrícula não encontrada"));

        Telefone_FuncEntity telefoneFunc = Telefone_FuncEntity.builder()
                .funcionario(funcionarioEntity)
                .telefone(telefoneFuncDTO.getTelefone())
                .build();

        return telefoneFuncRepository.save(telefoneFunc);
    }
}
