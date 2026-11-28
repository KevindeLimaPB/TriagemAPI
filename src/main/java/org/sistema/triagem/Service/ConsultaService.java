package org.sistema.triagem.Service;

import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.ConsultaEntity;
import org.sistema.triagem.Database.Model.MedicoEntity;
import org.sistema.triagem.Database.Model.PacienteEntity;
import org.sistema.triagem.Database.Repository.ConsultaRepository;
import org.sistema.triagem.Database.Repository.MedicoRepository;
import org.sistema.triagem.Database.Repository.PacienteRepository;
import org.sistema.triagem.Enums.Status;
import org.sistema.triagem.Exception.NotFoundException;
import org.sistema.triagem.dto.ConsultaDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    @Transactional
    public ConsultaEntity save(ConsultaDTO dto) throws NotFoundException {

        PacienteEntity paciente = buscarPaciente(dto.getPacienteCpf());
        MedicoEntity medico = buscarMedico(dto.getMedico());

        if (dto.getData_hora().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("A data da consulta deve estar no futuro.");
        }

        if (consultaRepository.existeConflito(medico, dto.getData_hora(), Status.CANCELADA)) {
            throw new IllegalStateException("Esse horário já está ocupado para o médico escolhido.");
        }

        ConsultaEntity consulta = ConsultaEntity.builder()
                .medico(medico)
                .paciente(paciente)
                .planosSaude(dto.getPlanosSaude())
                .especialidade(dto.getEspecialidadeConsulta())
                .dia_hora(dto.getData_hora())
                .status(dto.getStatus() != null ? dto.getStatus() : Status.EM_AGUARDO)
                .build();

        return consultaRepository.save(consulta);
    }

    @Transactional
    public void deletar(Integer id) {
        ConsultaEntity consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada com o ID: " + id));

        consulta.setStatus(Status.CANCELADA);
        consultaRepository.save(consulta);
    }


    private PacienteEntity buscarPaciente(String cpf) throws NotFoundException {
        String cpfLimpo = cpf == null ? "" : cpf.replaceAll("\\D", "");
        return pacienteRepository.findByUsuarioCpf(cpfLimpo)
                .orElseThrow(() -> new NotFoundException("Paciente não encontrado para o CPF informado."));
    }

    private MedicoEntity buscarMedico(String identificador) throws NotFoundException {
        return medicoRepository.findByCrm(identificador)
                .or(() -> medicoRepository.findByNome(identificador))
                .orElseThrow(() -> new NotFoundException("Médico não encontrado: " + identificador));
    }
}