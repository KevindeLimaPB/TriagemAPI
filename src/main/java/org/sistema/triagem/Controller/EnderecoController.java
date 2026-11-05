package org.sistema.triagem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.EnderecoEntity;
import org.sistema.triagem.Service.EnderecoService;
import org.sistema.triagem.dto.EnderecoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/end")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public EnderecoEntity save(@RequestBody @Valid EnderecoDTO enderecoDTO, @PathVariable("id") Integer id) throws Exception{
        return enderecoService.save(enderecoDTO, id);
    }

}
