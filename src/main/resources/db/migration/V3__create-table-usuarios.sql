CREATE TABLE usuarios (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    correo_electronico VARCHAR(255) NOT NULL,
    contrasena VARCHAR(100) NOT NULL,
    perfil_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (perfil_id) REFERENCES perfiles(id),
    UNIQUE (correo_electronico)
);