package org.sistema.triagem.Service;

import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.PacienteEntity;
import org.sistema.triagem.Database.Model.Telefone_pacEntity;
import org.sistema.triagem.Database.Repository.PacienteRepository;
import org.sistema.triagem.Database.Repository.Telefone_pacRepository;
import org.sistema.triagem.Exception.NotFoundException;
import org.sistema.triagem.dto.Telefone_pacDTO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelefonePacService {

    private final Telefone_pacRepository telefonePacRepository;

    private final PacienteRepository pacienteRepository;

    public Telefone_pacEntity save(Telefone_pacDTO telefonePacDTO) throws NotFoundException {
        PacienteEntity paciente = pacienteRepository
                .findById(telefonePacDTO.getPaciente())
                .orElseThrow(() -> new NotFoundException("ID não encontrado"));

        Telefone_pacEntity telefonePac = Telefone_pacEntity.builder()
                .paciente(paciente)
                .telefone(telefonePacDTO.getTelefone())
                .build();

        return telefonePacRepository.save(telefonePac);
    }

}
