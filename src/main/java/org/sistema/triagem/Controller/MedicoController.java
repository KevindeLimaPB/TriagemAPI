package org.sistema.triagem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.MedicoEntity;
import org.sistema.triagem.Service.MedicoService;
import org.sistema.triagem.dto.MedicoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/med")
@RequiredArgsConstructor
public class MedicoController {


    private final MedicoService medicoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MedicoEntity save(@Valid @RequestBody MedicoDTO medicoDTO) throws Exception {
        return medicoService.save(medicoDTO);
    }
}
