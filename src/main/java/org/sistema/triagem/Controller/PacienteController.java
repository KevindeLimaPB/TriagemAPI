package org.sistema.triagem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.PacienteEntity;
import org.sistema.triagem.Service.PacienteService;
import org.sistema.triagem.dto.PacienteDTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/pac")
public class PacienteController {

    private final PacienteService pacienteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PacienteEntity save(@RequestBody @Valid PacienteDTO pacienteDTO) throws Exception {
        return pacienteService.save(pacienteDTO);
    }
}
