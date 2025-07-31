create table topicos (
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensage text not null,
    fecha_creacion datetime not null,
    status varchar(20) not null,
    autor_id bigint not null,
    curso_id bigint not null,
    respuesta_id bigint not null,
    foreign key (autor_id) references usuarios(id),
    foreign key (curso_id) references cursos(id),
    primary key (id)
);