package org.sistema.triagem.Service;

import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.PacienteEntity;
import org.sistema.triagem.Database.Model.UsuarioEntity;
import org.sistema.triagem.Database.Repository.PacienteRepository;
import org.sistema.triagem.Database.Repository.UsuarioRepository;
import org.sistema.triagem.Exception.NotFoundException;
import org.sistema.triagem.dto.PacienteDTO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    private final UsuarioRepository usuarioRepository;

    public PacienteEntity save(PacienteDTO pacienteDTO) throws NotFoundException {
        UsuarioEntity usuarioEntity = usuarioRepository
                .findByCPF(pacienteDTO.getUsuario())
                .orElseThrow(() -> new NotFoundException("CPF não encontrado!"));

        PacienteEntity pacienteEntity = PacienteEntity.builder()
                .nome(pacienteDTO.getNome())
                .idade(pacienteDTO.getIdade())
                .dataNascimento(pacienteDTO.getData_Nascimento())
                .usuario(usuarioEntity)
                .build();

        return pacienteRepository.save(pacienteEntity);

    }
}
