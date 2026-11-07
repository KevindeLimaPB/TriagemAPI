package org.sistema.triagem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.FuncionarioEntity;
import org.sistema.triagem.Service.FuncionarioService;
import org.sistema.triagem.dto.FuncionarioDTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/func")
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "*")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FuncionarioEntity save(@RequestBody @Valid FuncionarioDTO funcionarioDTO) throws Exception {
        return funcionarioService.save(funcionarioDTO);
    }
}
