# Clínica Veterinaria — Backend REST API

Proyecto del curso IC-6821 Diseño de Software, TEC.

Sistema de gestión para una clínica veterinaria (propietarios, mascotas, 
veterinarios, consultas y medicamentos) implementado como API REST con 
Spring Boot y PostgreSQL.

## Cómo correrlo

Levantar la base de datos:
```bash
docker run --name veterinaria-db -e POSTGRES_PASSWORD=admin123 -e POSTGRES_DB=veterinaria -p 5432:5432 -d postgres
```

Configurar `application.properties` con esas credenciales y correr el proyecto desde IntelliJ.

La API queda disponible en `http://localhost:8080`.
