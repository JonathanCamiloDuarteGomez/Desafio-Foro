package com.cdg.Foro.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



public record DatosRegistroTopicoDTO(
        @NotBlank(message = "El título no puede estar vacío")
        String titulo,
        @NotBlank(message = "El mensage no puede estar vacío")
        String mensage,
        @NotNull(message = "El autor no puede estar vacío")
        Long autorId,
        @NotNull(message = "El curso no puede estar vacío")
        Long cursoId
) {
}
