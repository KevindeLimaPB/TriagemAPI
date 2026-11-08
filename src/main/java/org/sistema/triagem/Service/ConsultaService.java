package org.sistema.triagem.Service;


import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.ConsultaEntity;
import org.sistema.triagem.Database.Model.MedicoEntity;
import org.sistema.triagem.Database.Model.PacienteEntity;
import org.sistema.triagem.Database.Repository.ConsultaRepository;
import org.sistema.triagem.Database.Repository.MedicoRepository;
import org.sistema.triagem.Database.Repository.PacienteRepository;
import org.sistema.triagem.Exception.NotFoundException;
import org.sistema.triagem.dto.ConsultaDTO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;

    private final MedicoRepository medicoRepository;

    private final PacienteRepository pacienteRepository;

    public ConsultaEntity save(ConsultaDTO consultaDTO) throws NotFoundException {
        PacienteEntity paciente = pacienteRepository
                .findById(consultaDTO.getPaciente())
                .orElseThrow(() -> new NotFoundException("Paciente não encontrado"));

        MedicoEntity medico = medicoRepository
                .findById(consultaDTO.getMedico())
                .orElseThrow(() -> new NotFoundException("Médico não encontrado"));

        ConsultaEntity consulta = ConsultaEntity.builder()
                .medico(medico)
                .paciente(paciente)
                .especialidade(consultaDTO.getEspecialidadeConsulta())
                .dia_hora(consultaDTO.getData_hora())
                .build();

        return consultaRepository.save(consulta);
    }

}
