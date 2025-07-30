package com.cdg.Foro.domain.topico;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record DatosRegistroTopicoDTO(
        @NotBlank(message = "El título no puede estar vacío")
        String titulo,
        @NotBlank(message = "El mensage no puede estar vacío")
        String mensage,
        @NotBlank(message = "La fecha de creación no puede estar vacía")
        LocalDateTime fechaCreacion,
        @NotBlank(message = "El status no puede estar vacío")
        StatusTopico status
) {
}
