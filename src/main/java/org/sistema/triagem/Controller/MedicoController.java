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
import org.sistema.triagem.dto.MedicoPerfilDTO;
import org.sistema.triagem.dto.Records.MedicoBuscaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/med")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MedicoController {

    private final MedicoService medicoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MedicoEntity save(@Valid @RequestBody DadosMedicosDTO dadosMedicosDTO) throws Exception {
        return medicoService.save(dadosMedicosDTO);
    }

    @GetMapping("/perfil")
    public ResponseEntity<MedicoPerfilDTO> buscarPerfil(@RequestParam String matricula) throws Exception {
        // Exemplo buscando pelo CRM ou ID do médico logado
        MedicoPerfilDTO medico = medicoService.buscarPorMatricula(matricula);
        if (medico == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(medico);
    }

    @GetMapping("/buscar")
    public List<MedicoBuscaDTO> buscar(
            @RequestParam(required = false) EspecialidadeMedic especialidade,
            @RequestParam(required = false) String cidade) {
        return medicoService.buscar(especialidade, cidade);
    }
}
