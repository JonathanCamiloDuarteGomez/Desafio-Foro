package com.cdg.Foro.domain.topico;

import com.cdg.Foro.domain.curso.Curso;
import com.cdg.Foro.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensage;
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;


    public Topico(DatosRegistroTopicoDTO datosRegistroTopico) {
        this.id = null;
        this.titulo = datosRegistroTopico.titulo();
        this.mensage = datosRegistroTopico.mensage();
        this.fechaCreacion = LocalDateTime.now();
        this.status = datosRegistroTopico.status();
    }




}
