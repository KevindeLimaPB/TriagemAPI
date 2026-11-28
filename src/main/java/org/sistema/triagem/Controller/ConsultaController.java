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

    @GetMapping("/{cpf}")
    public ResponseEntity<List<ConsultaEntity>> listarPorPaciente(@PathVariable String cpf) {
        List<ConsultaEntity> consultas = consultaRepository.findByPacienteUsuarioCpf(cpf);
        return ResponseEntity.ok(consultas);
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
