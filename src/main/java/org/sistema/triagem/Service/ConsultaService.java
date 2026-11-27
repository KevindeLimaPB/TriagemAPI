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
        // 1. Busca o paciente pelo ID numérico enviado no DTO
        PacienteEntity paciente = pacienteRepository
                .findById(consultaDTO.getPaciente())
                .orElseThrow(() -> new NotFoundException("Paciente não encontrado"));

        // 2. Como o DTO recebe o médico como String (nome ou matrícula), buscamos adequadamente.
        // Se a sua MedicoEntity possui um campo de nome ou matrícula, ajuste o método de busca abaixo:
        MedicoEntity medico = medicoRepository
                .findByNome(consultaDTO.getMedico())
                .or(() -> medicoRepository.findByCrm(consultaDTO.getMedico()))
                .orElseThrow(() -> new NotFoundException("Médico não encontrado: " + consultaDTO.getMedico()));

        // 3. Monta a entidade de consulta com os dados mapeados
        ConsultaEntity consulta = ConsultaEntity.builder()
                .medico(medico)
                .paciente(paciente)
                .especialidade(consultaDTO.getEspecialidadeConsulta())
                .dia_hora(consultaDTO.getData_hora())
                .build();

        return consultaRepository.save(consulta);
    }
}