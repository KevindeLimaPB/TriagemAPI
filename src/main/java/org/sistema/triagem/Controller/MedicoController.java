package org.sistema.triagem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.MedicoEntity;
import org.sistema.triagem.Database.Repository.MedicoRepository;
import org.sistema.triagem.Enums.EspecialidadeConsulta;
import org.sistema.triagem.Enums.EspecialidadeMedic;
import org.sistema.triagem.Service.MedicoService;
import org.sistema.triagem.dto.DadosMedicosDTO;
import org.sistema.triagem.dto.MedicoDTO;
import org.sistema.triagem.dto.Records.MedicoBuscaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/med")
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoService medicoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MedicoEntity save(@Valid @RequestBody DadosMedicosDTO dadosMedicosDTO) throws Exception {
        return medicoService.save(dadosMedicosDTO);
    }

    @GetMapping("/buscar")
    public List<MedicoBuscaDTO> buscar(
            @RequestParam(required = false) EspecialidadeMedic especialidade,
            @RequestParam(required = false) String cidade) {
        return medicoService.buscar(especialidade, cidade);
    }
}
