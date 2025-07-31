package com.cdg.Foro.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizacionTopicoDTO(
        @NotBlank(message = "El mensaje no puede estar vacío") String mensage,
        @NotNull(message = "El status no puede estar vacío") StatusTopico status,
        @NotNull(message = "El autor no puede estar vacío") Long autorId,
        @NotNull(message = "El curso no puede estar vacío") Long cursoId
) {
    public DatosActualizacionTopicoDTO(Topico topico) {
        this(topico.getMensage(), topico.getStatus(), topico.getAutorId(), topico.getCursoId());
    }
}
