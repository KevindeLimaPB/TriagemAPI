package org.sistema.triagem.Service;

import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.EnderecoPacEntity;
import org.sistema.triagem.Database.Model.PacienteEntity;
import org.sistema.triagem.Database.Repository.EnderecoPacRepository;
import org.sistema.triagem.Database.Repository.PacienteRepository;
import org.sistema.triagem.Exception.NotFoundException;
import org.sistema.triagem.dto.EnderecoPacDTO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoPacService {

    private final EnderecoPacRepository enderecoPacRepository;

    private final PacienteRepository pacienteRepository;

    public EnderecoPacEntity save(EnderecoPacDTO enderecoPacDTO) throws NotFoundException {
        PacienteEntity paciente = pacienteRepository
                .findById(enderecoPacDTO.getPaciente())
                .orElseThrow(() -> new NotFoundException("ID não encontrado."));

        EnderecoPacEntity enderecoPacEntity = EnderecoPacEntity.builder()
                .paciente(paciente)
                .estado(enderecoPacDTO.getEstado())
                .cep(enderecoPacDTO.getCep())
                .rua(enderecoPacDTO.getRua())
                .numero(enderecoPacDTO.getNumero())
                .build();

        return enderecoPacRepository.save(enderecoPacEntity);
    }

}
