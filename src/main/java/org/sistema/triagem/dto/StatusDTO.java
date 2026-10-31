package org.sistema.triagem.dto;

import jakarta.validation.constraints.NotNull;
import org.sistema.triagem.Enums.Status;

public class StatusDTO {

    @NotNull
    private Status status;

    @NotNull
    private Integer consulta;
}
