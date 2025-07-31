// src/main/java/com/cdg/Foro/domain/respuesta/Respuesta.java
package com.cdg.Foro.domain.respuesta;

import com.cdg.Foro.domain.topico.Topico;
import com.cdg.Foro.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "respuesta")
@Table(name = "respuestas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    private Long topicoId;

    private Long autorId;

    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private Boolean solucion = false;

}