# 🚀 Foro API

[![Java](https://img.shields.io/badge/Java-17%2B-orange)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.0-green)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring%20Security-6.1.0-blue)](https://spring.io/projects/spring-security)
[![JWT](https://img.shields.io/badge/JWT-4.5.0-yellow)](https://jwt.io/)


## 📌 Descripción del Proyecto

**Foro** es una API RESTful desarrollada con **Spring Boot** que permite gestionar **tópicos y respuestas** en un foro en línea. La aplicación implementa una arquitectura limpia, con separación de responsabilidades entre controladores, servicios, repositorios y modelos de dominio.

## 🎯 Características Principales

### 🗂️ Gestión de Tópicos

- **Crear** nuevos tópicos con validación
- **Listar** tópicos con paginación y ordenamiento personalizable
- **Ver detalles** de un tópico específico
- **Actualizar** tópicos existentes
- **Eliminar** tópicos
- Validación para evitar tópicos duplicados (mismo título y mensaje)

### 💬 Gestión de Respuestas

- **Agregar** respuestas a tópicos existentes
- **Listar** respuestas de un tópico específico
- **Actualizar** respuestas
- **Eliminar** respuestas
- Validación de datos de entrada

## 🛠️ Tecnologías Utilizadas

### Backend
- Java 17
- Spring Boot 3.1.0
- Spring Security 6.1.0
- Spring Data JPA
- Spring Validation
- Lombok
- JWT (JSON Web Tokens)

### Base de Datos
- MySQL 8.0+
- Flyway (para migraciones)

### Herramientas de Desarrollo
- Maven
- Git
- IntelliJ IDEA (recomendado)

## 🔐 Autenticación

La API utiliza autenticación basada en JWT (JSON Web Tokens).

### Flujo de Autenticación

1. **Iniciar sesión** para obtener un token JWT
2. Incluir el token en el header `Authorization` de las peticiones posteriores
3. El token tiene una validez de 2 horas

### Endpoints de Autenticación

#### Iniciar Sesión
```http
POST /login
Content-Type: application/json

{
    "nombre": "usuario",
    "contrasena": "contraseña"
}
```

**Respuesta exitosa (200 OK):**
```json
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

#### Uso del Token
Incluir el token en el header de las peticiones:
```
Authorization: Bearer <token>
```

## 🚀 Empezando

### Requisitos Previos

- Java 17 o superior
- MySQL 8.0 o superior
- Maven 3.6+

### Instalación

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/JonathanCamiloDuarteGomez/Desafio-Foro.git
   cd foro-api
   ```

2. Configurar la base de datos:
   - Crear una base de datos MySQL llamada `foro_db`
   - Configurar las credenciales en `src/main/resources/application.properties`
   - Ejecutar el script SQL para crear el usuario inicial (ver sección de configuración)

3. Ejecutar la aplicación:
   ```bash
   mvn spring-boot:run
   ```

La aplicación estará disponible en `http://localhost:8080`

## ⚙️ Configuración

### Base de Datos

1. Crear la base de datos:
   ```sql
   CREATE DATABASE foro_db;
   ```

2. Crear un usuario administrador (opcional):
   ```sql
   INSERT INTO usuario (nombre, email, contrasena) 
   VALUES ('admin', 'admin@foro.com', '$2a$12$TuHashGeneradoAqui123456789012345678901234567890123456789012345678');
   ```
   
   Reemplaza el hash con uno generado con BCrypt para tu contraseña deseada.

### Variables de Entorno

Asegúrate de configurar estas propiedades en `application.properties`:

```properties
# Configuración de JWT
api.security.token.secret=frase-secreta-para-generar-token
api.security.token.expiration=7200 # 2 horas en segundos

# Configuración de la base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/foro_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

## 📚 Documentación de la API

### Ejemplos de Uso con JSON

A continuación se muestran ejemplos de cómo interactuar con la API utilizando JSON en herramientas como Postman o Insomnia.

#### Tópicos

##### 1. Crear un nuevo tópico
**Endpoint:** `POST /topicos`

**Headers:**
```
Content-Type: application/json
```

**Request Body:**
```json
{
    "titulo": "Duda sobre Spring Boot",
    "mensage": "¿Cuál es la mejor forma de manejar excepciones en Spring Boot?",
    "autorId": 1,
    "cursoId": 1
}
```

**Response (201 Created):**
```json
{
    "id": 1,
    "titulo": "Duda sobre Spring Boot",
    "mensage": "¿Cuál es la mejor forma de manejar excepciones en Spring Boot?",
    "fechaCreacion": "2025-08-02T15:30:00",
    "status": "NO_RESPONDIDO",
    "autorId": 1,
    "cursoId": 1
}
```

##### 2. Obtener un tópico por ID
**Endpoint:** `GET /topicos/1`

**Response (200 OK):**
```json
{
    "id": 1,
    "titulo": "Duda sobre Spring Boot",
    "mensage": "¿Cuál es la mejor forma de manejar excepciones en Spring Boot?",
    "fechaCreacion": "2025-08-02T15:30:00",
    "status": "NO_RESPONDIDO",
    "autor": {
        "id": 1,
        "nombre": "Juan Pérez"
    },
    "curso": {
        "id": 1,
        "nombre": "Spring Boot Avanzado"
    },
    "respuestas": []
}
```

##### 3. Actualizar un tópico
**Endpoint:** `PUT /topicos/1`

**Headers:**
```
Content-Type: application/json
```

**Request Body:**
```json
{
    "mensage": "¿Cuál es la mejor forma de manejar excepciones en Spring Boot? He intentado con @ControllerAdvice pero tengo dudas.",
    "status": "NO_SOLUCIONADO",
    "autorId": 1,
    "cursoId": 1
}
```

**Response (200 OK):**
```json
{
    "id": 1,
    "titulo": "Duda sobre Spring Boot",
    "mensage": "¿Cuál es la mejor forma de manejar excepciones en Spring Boot? He intentado con @ControllerAdvice pero tengo dudas.",
    "status": "NO_SOLUCIONADO"
}
```

##### 4. Listar tópicos (con paginación)
**Endpoint:** `GET /topicos?page=0&size=10&sort=fechaCreacion,desc`

**Response (200 OK):**
```json
{
    "content": [
        {
            "id": 1,
            "titulo": "Duda sobre Spring Boot",
            "mensage": "¿Cuál es la mejor forma de manejar excepciones en Spring Boot?",
            "fechaCreacion": "2025-08-02T15:30:00",
            "status": "NO_SOLUCIONADO",
            "autor": {
                "id": 1,
                "nombre": "Juan Pérez"
            }
        }
    ],
    "pageable": {
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 10,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 10,
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "first": true,
    "numberOfElements": 1,
    "empty": false
}
```

#### Respuestas

##### 1. Agregar una respuesta a un tópico
**Endpoint:** `POST /topicos/1/respuestas`

**Headers:**
```
Content-Type: application/json
```

**Request Body:**
```json
{
    "mensaje": "Puedes usar @ControllerAdvice junto con @ExceptionHandler. Te recomiendo revisar la documentación oficial de Spring.",
    "autorId": 2,
    "solucion": true
}
```

**Response (201 Created):**
```json
{
    "id": 1,
    "mensaje": "Puedes usar @ControllerAdvice junto con @ExceptionHandler. Te recomiendo revisar la documentación oficial de Spring.",
    "fechaCreacion": "2025-08-02T15:35:00",
    "solucion": true,
    "autorId": 2,
    "topicoId": 1
}
```

### Variables de Entorno para Pruebas

Para configurar las variables de entorno en Postman o Insomnia, puedes usar los siguientes valores:

- `baseUrl`: `http://localhost:8080`
- `topicoId`: ID del tópico creado (se actualiza automáticamente después de crear un tópico)
- `respuestaId`: ID de la respuesta creada (se actualiza automáticamente después de crear una respuesta)

## 🏗️ Estructura del Proyecto

```
src/
├── main/
│   ├── java/com/cdg/Foro/
│   │   ├── controller/        # Controladores REST
│   │   │   ├── TopicoController.java
│   │   │   └── RespuestaController.java
│   │   ├── domain/            # Modelos y lógica de negocio
│   │   │   ├── topico/        # Entidades y DTOs de tópicos
│   │   │   └── respuesta/     # Entidades y DTOs de respuestas
│   │   └── infraestructura/   # Configuración y utilidades
│   └── resources/
│       ├── db/migration/      # Scripts de migración Flyway
│       └── application.properties
└── test/                      # Pruebas unitarias y de integración
```



<div align="center">
  Hecho con ❤️ por Jonathan Camilo Duarte Gomez | 2025
</div>
