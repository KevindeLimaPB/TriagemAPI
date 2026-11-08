package org.sistema.triagem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.EnderecoPacEntity;
import org.sistema.triagem.Service.EnderecoPacService;
import org.sistema.triagem.dto.EnderecoPacDTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/endpac")
@Validated
@RequiredArgsConstructor
public class EnderecoPacController {

    private final EnderecoPacService enderecoPacService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnderecoPacEntity save(@RequestBody @Valid EnderecoPacDTO enderecoPacDTO) throws Exception {
        return enderecoPacService.save(enderecoPacDTO);
    }
}
