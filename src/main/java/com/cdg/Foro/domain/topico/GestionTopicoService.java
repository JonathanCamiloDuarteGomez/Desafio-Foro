package com.cdg.Foro.domain.topico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GestionTopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    public void registrarTopico(DatosRegistroTopicoDTO datosRegistroTopico) {

        //La API no debe permitir el registro de tópicos duplicados (con el mismo título y mensaje).
        if (topicoRepository.existsByTituloAndMensage(datosRegistroTopico.titulo(), datosRegistroTopico.mensage())) {
            throw new IllegalArgumentException("El tópico ya existe");
        }else{
            topicoRepository.save(new Topico(datosRegistroTopico));
        }

    }
    public Page<Topico> listadoTopicos( Pageable pageable) {//pageable de Spring data
        return topicoRepository.findAll(pageable);
    }
    public Optional<Topico> detalleTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new IllegalArgumentException("Tópico no encontrado");
        }
        return topicoRepository.findById(id);
    }

    public Topico getReferenceById(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new IllegalArgumentException("Tópico no encontrado");
        }
        return topicoRepository.getReferenceById(id);
    }
}
