package org.sistema.triagem.Service;

import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.FuncionarioEntity;
import org.sistema.triagem.Database.Model.MedicoEntity;
import org.sistema.triagem.Database.Repository.FuncionarioRepository;
import org.sistema.triagem.Database.Repository.MedicoRepository;
import org.sistema.triagem.Enums.Profissao;
import org.sistema.triagem.Exception.NotAuthorized;
import org.sistema.triagem.Exception.NotFoundException;
import org.sistema.triagem.dto.MedicoDTO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicoService {

    private final MedicoRepository medicoRepository;

    private final FuncionarioRepository funcionarioRepository;

    public MedicoEntity save(MedicoDTO medicoDTO) throws NotFoundException {
        FuncionarioEntity entity = funcionarioRepository.findById(medicoDTO.getFuncionario())
                .orElseThrow(() -> new NotFoundException("ID inválido"));

        if (entity.getProfissao() != Profissao.MEDICO){
            throw new NotAuthorized("Não autorizado");
        }

        MedicoEntity medico = MedicoEntity.builder()
                .crm(medicoDTO.getCrm())
                .especialidade(medicoDTO.getEspecialidade())
                .preco_consulta(medicoDTO.getPreco_consulta())
                .tele_consulta(medicoDTO.getTele_consulta())
                .funcionario(entity)
                .build();

        return medicoRepository.save(medico);
    }

}
