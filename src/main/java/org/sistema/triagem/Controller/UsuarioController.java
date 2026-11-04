package org.sistema.triagem.Controller;

import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Database.Model.UsuarioEntity;
import org.sistema.triagem.Service.UsuarioService;
import org.sistema.triagem.dto.UsuarioDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioEntity save(@RequestBody UsuarioDTO usuarioDTO) throws Exception{
        return usuarioService.save(usuarioDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioEntity> findAll(){
        return usuarioService.findAll();
    }
}
