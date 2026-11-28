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
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    @Transactional
    public ConsultaEntity save(ConsultaDTO consultaDTO) throws NotFoundException {
        PacienteEntity paciente = pacienteRepository
                .findById(consultaDTO.getPaciente())
                .orElseThrow(() -> new NotFoundException("Paciente não encontrado"));


        MedicoEntity medico = medicoRepository
                .findByNome(consultaDTO.getMedico())
                .or(() -> medicoRepository.findByCrm(consultaDTO.getMedico()))
                .orElseThrow(() -> new NotFoundException("Médico não encontrado: " + consultaDTO.getMedico()));

        ConsultaEntity consulta = ConsultaEntity.builder()
                .medico(medico)
                .paciente(paciente)
                .especialidade(consultaDTO.getEspecialidadeConsulta())
                .dia_hora(consultaDTO.getData_hora())
                .build();

        return consultaRepository.save(consulta);
    }

    public void deletar(Integer id) {
        if (!consultaRepository.existsById(id)) {
            throw new RuntimeException("Consulta não encontrada com o ID: " + id);
        }
        consultaRepository.deleteById(id);
    }
}