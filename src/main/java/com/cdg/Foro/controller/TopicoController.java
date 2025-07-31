package com.cdg.Foro.controller;

import com.cdg.Foro.domain.topico.DatosActualizacionTopicoDTO;
import com.cdg.Foro.domain.topico.DatosRegistroTopicoDTO;
import com.cdg.Foro.domain.topico.GestionTopicoService;
import com.cdg.Foro.domain.topico.Topico;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    @GetMapping
    public  ResponseEntity<Page<Topico>> listar( @PageableDefault(size = 10,sort={"fechaCreacion"}, direction = Sort.Direction.DESC)Pageable pageable) {
        return ResponseEntity.ok(serviceTopico.listadoTopicos(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Topico>> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(serviceTopico.detalleTopico(id));
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizacionTopicoDTO actualizar) {
        Topico topico = serviceTopico.actualizarTopico(id,actualizar);
        return ResponseEntity.ok((new DatosActualizacionTopicoDTO(topico)));
    }

}
