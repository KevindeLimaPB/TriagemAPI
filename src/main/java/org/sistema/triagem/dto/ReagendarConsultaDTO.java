package org.sistema.triagem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ReagendarConsultaDTO {

    @NotNull(message = "A nova data e hora são obrigatórias.")
    private LocalDateTime data_hora;
}