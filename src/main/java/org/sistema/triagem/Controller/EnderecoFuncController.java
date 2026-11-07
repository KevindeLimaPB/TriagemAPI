package org.sistema.triagem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.EnderecoFuncEntity;
import org.sistema.triagem.Service.EnderecoService;
import org.sistema.triagem.dto.EnderecoFuncDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/end")
@RequiredArgsConstructor
public class EnderecoFuncController {

    private final EnderecoService enderecoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnderecoFuncEntity save(@RequestBody @Valid EnderecoFuncDTO enderecoDTO) throws Exception {
        return enderecoService.save(enderecoDTO);
    }

}
