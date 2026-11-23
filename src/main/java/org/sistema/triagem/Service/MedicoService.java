package org.sistema.triagem.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.EnderecoFuncEntity;
import org.sistema.triagem.Database.Model.FuncionarioEntity;
import org.sistema.triagem.Database.Model.MedicoEntity;
import org.sistema.triagem.Database.Model.UsuarioEntity;
import org.sistema.triagem.Database.Repository.EnderecoFuncRepository;
import org.sistema.triagem.Database.Repository.FuncionarioRepository;
import org.sistema.triagem.Database.Repository.MedicoRepository;
import org.sistema.triagem.Database.Repository.UsuarioRepository;
import org.sistema.triagem.Enums.Profissao;
import org.sistema.triagem.Exception.Conflict;
import org.sistema.triagem.Exception.NotAuthorized;
import org.sistema.triagem.Exception.NotFoundException;
import org.sistema.triagem.dto.DadosMedicosDTO;
import org.sistema.triagem.dto.MedicoDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicoService {

    private final EnderecoFuncRepository enderecoFuncRepository;

    private final MedicoRepository medicoRepository;

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    private final FuncionarioRepository funcionarioRepository;

    @Transactional
    public MedicoEntity save(DadosMedicosDTO dadosMedicosDTO) throws Conflict, NotFoundException {
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

        MedicoEntity medico = new MedicoEntity();
        medico.setCrm(dadosMedicosDTO.getCrm());
        medico.setEspecialidade(dadosMedicosDTO.getEspecialidade());
        medico.setPreco_consulta(dadosMedicosDTO.getPrecoConsulta());
        medico.setFuncionario(func);

        return medicoRepository.save(medico);

    }



}
