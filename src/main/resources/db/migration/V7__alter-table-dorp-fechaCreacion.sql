alter table respuestas drop column fechaCreacion;
alter table respuestas add column fecha_creacion datetime not null;