package org.sistema.triagem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.ConsultaEntity;
import org.sistema.triagem.Database.Repository.ConsultaRepository;
import org.sistema.triagem.Service.ConsultaService;
import org.sistema.triagem.dto.ConsultaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consulta")
@RequiredArgsConstructor
@Validated
public class ConsultaController {

    private final ConsultaService consultaService;
    private final ConsultaRepository consultaRepository;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsultaEntity save(@RequestBody @Valid ConsultaDTO consultaDTO) throws Exception {
        return consultaService.save(consultaDTO);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<ConsultaEntity>> listarPorPaciente(@PathVariable Integer pacienteId) {
        List<ConsultaEntity> consultas = consultaRepository.findByPacienteId(pacienteId);
        return ResponseEntity.ok(consultas);
    }
}
