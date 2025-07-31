// src/main/java/com/cdg/Foro/domain/respuesta/DatosRegistroRespuestaDTO.java
package com.cdg.Foro.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroRespuestaDTO(
        @NotBlank
        String mensaje,

        @NotNull
        Long topicoId,

        @NotNull
        Long autorId
) {}