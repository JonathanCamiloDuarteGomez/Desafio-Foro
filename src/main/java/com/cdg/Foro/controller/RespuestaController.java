// src/main/java/com/cdg/Foro/controller/RespuestaController.java
package com.cdg.Foro.controller;

import com.cdg.Foro.domain.respuesta.DatosRegistroRespuestaDTO;
import com.cdg.Foro.domain.respuesta.GestionRespuestaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private GestionRespuestaService respuestaService;

    @PostMapping
    @Transactional
    public ResponseEntity registrarRespuesta(@RequestBody @Valid DatosRegistroRespuestaDTO datos) {
        respuestaService.registrarRespuesta(datos);
        return ResponseEntity.ok().build();
    }
}