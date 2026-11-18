package org.sistema.triagem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.Telefone_FuncEntity;
import org.sistema.triagem.Database.Model.Telefone_pacEntity;
import org.sistema.triagem.Service.TelefonePacService;
import org.sistema.triagem.dto.Telefone_funcDTO;
import org.sistema.triagem.dto.Telefone_pacDTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/telpac")
@Validated
@RequiredArgsConstructor
public class TelefonePacController {

    private final TelefonePacService telefonePacService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Telefone_pacEntity save(@RequestBody @Valid Telefone_pacDTO telefonePacDTO) throws Exception {
        return telefonePacService.save(telefonePacDTO);
    }
}
