package com.cdg.Foro.domain.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Table(name = "topicos")
@Entity(name = "topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor

@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensage;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    private Long autorId;

    private Long cursoId;
    private Long respuesta_id;


    public Topico(DatosRegistroTopicoDTO datosRegistroTopico) {
        this.id = null;
        this.titulo = datosRegistroTopico.titulo();
        this.mensage = datosRegistroTopico.mensage();
        this.fechaCreacion = LocalDateTime.now();
        this.status = StatusTopico.EN_CURSO;
        this.autorId = datosRegistroTopico.autorId();
        this.cursoId = datosRegistroTopico.cursoId();
        this.respuesta_id = null;
    }
    public void actualizarTopico(DatosActualizacionTopicoDTO actualizar) {

        if (actualizar.mensage() != null) {
            this.mensage = actualizar.mensage();
        }
        if (actualizar.status() != null) {
            this.status = actualizar.status();
        }
        if (actualizar.autorId() != null) {
            this.autorId = actualizar.autorId();
        }
        if (actualizar.cursoId() != null) {
            this.cursoId = actualizar.cursoId();
        }

    }




}
