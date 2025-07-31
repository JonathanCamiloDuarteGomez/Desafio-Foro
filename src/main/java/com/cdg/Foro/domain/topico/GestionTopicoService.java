package com.cdg.Foro.domain.topico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
