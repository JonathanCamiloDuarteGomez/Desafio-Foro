package com.cdg.Foro.domain.topico;

import com.cdg.Foro.domain.curso.Curso;
import com.cdg.Foro.domain.usuario.Usuario;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

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
