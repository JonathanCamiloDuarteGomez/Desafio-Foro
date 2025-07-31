package com.cdg.Foro.controller;

import com.cdg.Foro.domain.topico.DatosRegistroTopicoDTO;
import com.cdg.Foro.domain.topico.GestionTopicoService;
import com.cdg.Foro.domain.topico.Topico;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("topicos")
public class TopicoController {
    @Autowired
    private GestionTopicoService serviceTopico;

    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopicoDTO datosRegistroTopico) {
        serviceTopico.registrarTopico(datosRegistroTopico);
        return ResponseEntity.ok().build();
    }

}
