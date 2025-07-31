// src/main/java/com/cdg/Foro/domain/respuesta/GestionRespuestaService.java
package com.cdg.Foro.domain.respuesta;

import com.cdg.Foro.domain.topico.Topico;
import com.cdg.Foro.domain.topico.TopicoRepository;
import com.cdg.Foro.domain.usuario.Usuario;
import com.cdg.Foro.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GestionRespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void registrarRespuesta(DatosRegistroRespuestaDTO datos) {
        // Verifica que el tópico exista
        if (!topicoRepository.existsById(datos.topicoId())) {
            throw new IllegalArgumentException("Tópico no encontrado");
        }

        // Verifica que el usuario exista
        if (!usuarioRepository.existsById(datos.autorId())) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }

        Respuesta respuesta = new Respuesta(
                null,
                datos.mensaje(),
                datos.topicoId(),
                datos.autorId(),
                LocalDateTime.now(),
                false
        );

        respuestaRepository.save(respuesta);
    }
}