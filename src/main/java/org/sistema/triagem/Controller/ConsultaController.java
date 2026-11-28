package org.sistema.triagem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.ConsultaEntity;
import org.sistema.triagem.Database.Repository.ConsultaRepository;
import org.sistema.triagem.Exception.NotFoundException;
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
@CrossOrigin(origins = "*")
@Validated
public class ConsultaController {

    private final ConsultaService consultaService;
    private final ConsultaRepository consultaRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsultaEntity save(@RequestBody @Valid ConsultaDTO dto) throws NotFoundException {
        return consultaService.save(dto);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<List<ConsultaEntity>> listarPorPaciente(@PathVariable String cpf) {
        String cpfLimpo = cpf.replaceAll("\\D", "");
        return ResponseEntity.ok(consultaRepository.findByPacienteUsuarioCpf(cpfLimpo));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarConsulta(@PathVariable Integer id) {
        consultaService.deletar(id);
    }

    @GetMapping("/detalhes/{id}")
    public ResponseEntity<ConsultaEntity> buscarPorId(@PathVariable Integer id) {
        return consultaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}