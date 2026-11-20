package org.sistema.triagem.Service.Dados;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.PacienteEntity;
import org.sistema.triagem.Database.Model.Telefone_pacEntity;
import org.sistema.triagem.Database.Model.UsuarioEntity;
import org.sistema.triagem.Database.Repository.PacienteRepository;
import org.sistema.triagem.Database.Repository.Telefone_pacRepository;
import org.sistema.triagem.Database.Repository.UsuarioRepository;
import org.sistema.triagem.Exception.Conflict;
import org.sistema.triagem.Exception.NotFoundException;
import org.sistema.triagem.dto.DadosPacientesDTO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DadosPacientes {

    private final UsuarioRepository usuarioRepository;
    private final Telefone_pacRepository telefonePacRepository;
    private final PacienteRepository pacienteRepository;
    @Transactional
    public void perfilPaciente(DadosPacientesDTO dadosPacientesDTO)
            throws NotFoundException, Conflict {

        UsuarioEntity usuario = usuarioRepository.findByCPF(dadosPacientesDTO.getCpf())
                .orElseThrow(() -> new NotFoundException("CPF não encontrado!"));

        usuario.setNome(dadosPacientesDTO.getNome());
        usuario.setLogin(dadosPacientesDTO.getEmail());

        usuarioRepository.save(usuario);

        PacienteEntity paciente = pacienteRepository.findByUsuario(usuario)
                .orElseGet(() -> {
                    PacienteEntity novoPaciente = new PacienteEntity();
                    novoPaciente.setUsuario(usuario);
                    return novoPaciente;
                });
        paciente.setAltura(dadosPacientesDTO.getAltura());
        paciente.setPeso(dadosPacientesDTO.getPeso());
        paciente.setDataNascimento(dadosPacientesDTO.getDataNascimento());
        paciente.setSexoBiologico(dadosPacientesDTO.getSexoBiologico());
        paciente.setGeneros(dadosPacientesDTO.getGenero());

        pacienteRepository.save(paciente);

        Telefone_pacEntity telefonePac = telefonePacRepository.findByPaciente(paciente)
                .orElseThrow(() -> new NotFoundException("Telefone não encontrado!"));

        telefonePac.setTelefone(dadosPacientesDTO.getTelefone());

        telefonePacRepository.save(telefonePac);
    }



    public DadosPacientesDTO obterDadosPorCpf(String cpf) throws NotFoundException {

        // Busca o usuário pelo CPF
        UsuarioEntity usuario = usuarioRepository.findByCPF(cpf)
                .orElseThrow(() -> new NotFoundException("CPF não encontrado!"));

        // Busca o paciente vinculado ao usuário
        PacienteEntity paciente = pacienteRepository.findByUsuario(usuario)
                .orElse(null);

        // Cria o DTO
        DadosPacientesDTO dto = new DadosPacientesDTO();

        // Dados da tabela usuario
        dto.setCpf(usuario.getCpf());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getLogin());

        // Se existir paciente, carrega seus dados
        if (paciente != null) {

            dto.setDataNascimento(paciente.getDataNascimento());
            dto.setSexoBiologico(paciente.getSexoBiologico());
            dto.setGenero(paciente.getGeneros());
            dto.setPeso(paciente.getPeso());
            dto.setAltura(paciente.getAltura());

            // Busca o telefone vinculado ao paciente
            Telefone_pacEntity telefonePac = telefonePacRepository
                    .findByPaciente(paciente)
                    .orElse(null);

            if (telefonePac != null) {
                dto.setTelefone(telefonePac.getTelefone());
            }
        }

        return dto;
    }

}
