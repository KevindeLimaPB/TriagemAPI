package org.sistema.triagem.Controller.Dados;

import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Service.Dados.DadosPacientes;
import org.sistema.triagem.dto.DadosPacientesDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pac")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DadosPacientesController {

   private final DadosPacientes dadosPacientes;

    @PutMapping("/perfil")
    public ResponseEntity<String> atualizarPerfil(
            @RequestBody DadosPacientesDTO dadosPacientesDTO) {

        try {
            dadosPacientes.perfilPaciente(dadosPacientesDTO);
            return ResponseEntity.ok("Perfil atualizado!");

        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity
                    .status(500)
                    .body("ERRO: " + e.getClass().getName()
                            + " - " + e.getMessage());
        }
    }

    @GetMapping("/perfil/find")
    public ResponseEntity<DadosPacientesDTO> buscarPerfil(@RequestParam String cpf) {
        try {
            DadosPacientesDTO dto = dadosPacientes.obterDadosPorCpf(cpf);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
