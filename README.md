# 📌 Descripción del Proyecto

**Foro** es una API RESTful desarrollada con **Spring Boot** que permite gestionar **tópicos y respuestas** en un foro en línea. La aplicación implementa una arquitectura limpia, con separación de responsabilidades entre controladores, servicios, repositorios y modelos de dominio.

---

# 🛠️ Tecnologías Principales

- **Backend**: Java 17, Spring Boot  
- **Base de datos**: MySQL  
- **ORM**: Spring Data JPA  
- **Migraciones**: Flyway  
- **Validación**: Bean Validation (JSR-380)  
- **Documentación**: (Se recomienda implementar OpenAPI/Swagger)  

---

# 📋 Características Principales

### ✅ Gestión de Tópicos

- Crear nuevos tópicos con validación
- Listar tópicos con paginación y ordenamiento
- Obtener detalles de un tópico específico
- Actualizar tópicos existentes
- Eliminar tópicos
- Validación para evitar tópicos duplicados (mismo título y mensaje)

### ✅ Gestión de Respuestas

- Crear respuestas para un tópico
- Listar respuestas de un tópico
- Actualizar respuestas
- Eliminar respuestas

---

# 🚀 Estructura del Proyecto

```bash
src/
├── main/
│   ├── java/com/cdg/Foro/
│   │   ├── controller/        # Controladores REST
│   │   ├── domain/            # Modelos y lógica de negocio
│   │   └── infraestructura/   # Configuración y utilidades
│   └── resources/
│       └── db/migration/      # Scripts de migración Flyway
