create table respuestas(
    id bigint not null auto_increment,
    mensage varchar(100) not null,
    topico_id bigint not null,
    fechaCreacion datetime not null,
    autor_id bigint not null,
    solucion text not null,
    primary key (id),
    foreign key (topico_id) references topicos(id),
    foreign key (autor_id) references usuarios(id)
);