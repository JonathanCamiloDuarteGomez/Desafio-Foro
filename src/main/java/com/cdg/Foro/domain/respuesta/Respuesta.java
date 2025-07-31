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
    @Column(name = "mensage")
    private String mensaje;

    private Long topicoId;

    private Long autorId;
    private LocalDateTime fecha_creacion;
    private Boolean solucion ;

    public Respuesta(DatosRegistroRespuestaDTO datos) {
        this.id = null;
        this.mensaje = datos.mensaje();
        this.topicoId = datos.topicoId();
        this.autorId = datos.autorId();
        this.fecha_creacion = LocalDateTime.now();
        this.solucion = true;
    }

}