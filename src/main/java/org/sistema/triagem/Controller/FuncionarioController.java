package org.sistema.triagem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.FuncionarioEntity;
import org.sistema.triagem.Service.FuncionarioService;
import org.sistema.triagem.Service.MedicoService;
import org.sistema.triagem.dto.DadosMedicosDTO;
import org.sistema.triagem.dto.FuncionarioDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/func")
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "*")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    private final MedicoService medicoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FuncionarioEntity save(@RequestBody @Valid FuncionarioDTO funcionarioDTO) throws Exception {
        return funcionarioService.save(funcionarioDTO);
    }

    @GetMapping("/nome")
    public ResponseEntity<String> obterNomePorCpf(DadosMedicosDTO dadosMedicosDTO) {
        try {
            String nome = funcionarioService.obterNomePorCpf(dadosMedicosDTO);
            return ResponseEntity.ok(nome);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
