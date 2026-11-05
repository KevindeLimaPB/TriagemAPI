package org.sistema.triagem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.UsuarioEntity;
import org.sistema.triagem.Service.UsuarioService;
import org.sistema.triagem.dto.UsuarioDTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Validated
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioEntity save(@RequestBody @Valid UsuarioDTO usuarioDTO) throws Exception{
        return usuarioService.save(usuarioDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioEntity> findAll(){
        return usuarioService.findAll();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody UsuarioDTO usuarioDTO) throws Exception {
        usuarioService.updatePassword(usuarioDTO);
    }
}
