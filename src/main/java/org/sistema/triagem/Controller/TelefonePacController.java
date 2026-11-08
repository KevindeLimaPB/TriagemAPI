package org.sistema.triagem.Controller;

import lombok.RequiredArgsConstructor;
import org.sistema.triagem.Service.TelefonePacService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/telpac")
@Validated
@RequiredArgsConstructor
public class TelefonePacController {

    private final TelefonePacService telefonePacService;
}
