package org.sistema.triagem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.EnderecoFuncEntity;
import org.sistema.triagem.Service.EnderecoFuncService;
import org.sistema.triagem.dto.EnderecoFuncDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/endfunc")
@RequiredArgsConstructor
public class EnderecoFuncController {

    private final EnderecoFuncService enderecoFuncService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnderecoFuncEntity save(@RequestBody @Valid EnderecoFuncDTO enderecoDTO) throws Exception {
        return enderecoFuncService.save(enderecoDTO);
    }

}
