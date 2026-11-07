package org.sistema.triagem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.Telefone_FuncEntity;
import org.sistema.triagem.Service.TelefoneFuncService;
import org.sistema.triagem.dto.Telefone_funcDTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/telfunc")
@RestController
@RequiredArgsConstructor
@Validated
public class TelefoneFucController {

    private final TelefoneFuncService telefoneFuncService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Telefone_FuncEntity save(@RequestBody @Valid Telefone_funcDTO telefoneFuncDTO) throws Exception {
        return telefoneFuncService.save(telefoneFuncDTO);
    }
}
