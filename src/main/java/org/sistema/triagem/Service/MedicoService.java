package org.sistema.triagem.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.*;
import org.sistema.triagem.Database.Repository.*;
import org.sistema.triagem.Enums.EspecialidadeConsulta;
import org.sistema.triagem.Enums.EspecialidadeMedic;
import org.sistema.triagem.Exception.Conflict;
import org.sistema.triagem.Exception.NotFoundException;
import org.sistema.triagem.dto.DadosMedicosDTO;
import org.sistema.triagem.dto.MedicoPerfilDTO;
import org.sistema.triagem.dto.Records.MedicoBuscaDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoService {

    private final EnderecoFuncRepository enderecoFuncRepository;

    private final MedicoRepository medicoRepository;

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    private final Telefone_FuncRepository telefoneFuncRepository;

    private final FuncionarioRepository funcionarioRepository;

    @Transactional
    public MedicoEntity save(DadosMedicosDTO dadosMedicosDTO) throws Conflict {
        if (this.usuarioRepository.existsByCpf(dadosMedicosDTO.getCpf())) {
            throw new Conflict("Já existe um usuário cadastrado com este CPF.");
        }

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setCpf(dadosMedicosDTO.getCpf());
        usuario.setLogin(dadosMedicosDTO.getEmail());
        usuario.setNome(dadosMedicosDTO.getNome());
        usuario.setSenha(passwordEncoder.encode(dadosMedicosDTO.getSenha()));
        usuario.setEnumUser(dadosMedicosDTO.getRole());
        UsuarioEntity usuarioSalvo = usuarioRepository.save(usuario);

        FuncionarioEntity funcionario = new FuncionarioEntity();
        funcionario.setMatricula(dadosMedicosDTO.getMatricula());
        funcionario.setUsuario(usuarioSalvo);
        funcionario.setProfissao(dadosMedicosDTO.getProfissao());
        FuncionarioEntity func = funcionarioRepository.save(funcionario);

        EnderecoFuncEntity enderecoFunc = new EnderecoFuncEntity();
        enderecoFunc.setCidade(dadosMedicosDTO.getCidade());
        enderecoFunc.setBairro(dadosMedicosDTO.getBairro());
        enderecoFunc.setRua(dadosMedicosDTO.getRua());
        enderecoFunc.setFuncionario_id(func);
        enderecoFuncRepository.save(enderecoFunc);

        String telefone = dadosMedicosDTO.getTelefone();

        if (telefone != null && !telefone.isBlank()) {
            if (!telefone.matches("\\(\\d{2}\\) \\d{5}-\\d{4}")) {
                throw new Conflict("Telefone inválido. Use o formato (00) 00000-0000");
            }

            Telefone_FuncEntity telefoneFunc = new Telefone_FuncEntity();
            telefoneFunc.setTelefone(telefone);
            telefoneFunc.setFuncionario(func);
            telefoneFuncRepository.save(telefoneFunc);
        }
        MedicoEntity medico = new MedicoEntity();

        if (dadosMedicosDTO.getCrm() == null || dadosMedicosDTO.getCrm().isBlank()) {
            throw new Conflict("Informe o CRM.");
        }
        if (medicoRepository.existsById(dadosMedicosDTO.getCrm())) {
            throw new Conflict("Já existe um médico cadastrado com este CRM.");
        }

        medico.setCrm(dadosMedicosDTO.getCrm());
        medico.setEspecialidade(dadosMedicosDTO.getEspecialidade());
        medico.setPreco_consulta(dadosMedicosDTO.getPrecoConsulta());

        String apresention = dadosMedicosDTO.getApresentacao() == null || dadosMedicosDTO.getApresentacao().isBlank() ?
                "Profissional cadastrado na plataforma. Entre em contato para saber mais." :
                dadosMedicosDTO.getApresentacao();

        medico.setApresentacao(apresention);
        medico.setFuncionario(func);

        return medicoRepository.save(medico);

    }


    public List<MedicoBuscaDTO> buscar(EspecialidadeMedic especialidade, String cidade) {
        String cidadeFiltro = (cidade == null || cidade.isBlank()) ? null : cidade.trim();
        return medicoRepository.buscar(especialidade, cidadeFiltro);
    }

    public MedicoPerfilDTO buscarPorMatricula(String matricula) throws NotFoundException {
        // Busca direta sem Optional, verificando se veio nulo
        FuncionarioEntity funcionario = funcionarioRepository.findByMatriculas(matricula);
        if (funcionario == null) {
            throw new NotFoundException("Funcionário não encontrado com a matrícula: " + matricula);
        }

        MedicoEntity medico = medicoRepository.findByFuncionario(funcionario)
                .orElseThrow(() -> new NotFoundException("Médico não encontrado para esta matrícula."));

        MedicoPerfilDTO dto = new MedicoPerfilDTO();
        dto.setNome(funcionario.getUsuario() != null ? funcionario.getUsuario().getNome() : "");
        dto.setMatricula(funcionario.getMatricula());
        dto.setCrm(medico.getCrm());
        dto.setEspecialidade(medico.getEspecialidade() != null ? medico.getEspecialidade().name() : "");

        EnderecoFuncEntity endereco = enderecoFuncRepository.findByFuncionario(funcionario).orElse(null);
        if (endereco != null) {
            dto.setCidade(endereco.getCidade());
            dto.setRua(endereco.getRua());
        } else {
            dto.setCidade("");
            dto.setRua("");
        }

        dto.setPrecoConsulta(medico.getPreco_consulta());
        return dto;
    }

    @Transactional
    public MedicoPerfilDTO atualizarPorMatricula(MedicoPerfilDTO dados) throws NotFoundException {
        FuncionarioEntity funcionario = funcionarioRepository.findByMatriculas(dados.getMatricula());
        if (funcionario == null) {
            throw new NotFoundException("Funcionário não encontrado.");
        }

        MedicoEntity medico = medicoRepository.findByFuncionario(funcionario)
                .orElseThrow(() -> new NotFoundException("Médico não encontrado."));

        if (dados.getNome() != null && !dados.getNome().isBlank() && funcionario.getUsuario() != null) {
            funcionario.getUsuario().setNome(dados.getNome());
            usuarioRepository.save(funcionario.getUsuario());
        }

        medico.setPreco_consulta(dados.getPrecoConsulta());
        medicoRepository.save(medico);

        EnderecoFuncEntity endereco = enderecoFuncRepository.findByFuncionario(funcionario).orElseGet(() -> {
            EnderecoFuncEntity novo = new EnderecoFuncEntity();
            novo.setFuncionario_id(funcionario);
            return novo;
        });

        endereco.setCidade(dados.getCidade());
        endereco.setRua(dados.getRua());
        enderecoFuncRepository.save(endereco);

        return dados;
    }
}
