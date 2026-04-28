# 🐾 Clínica Veterinaria — Backend REST API

**Curso:** IC-6821 Diseño de Software  
**Institución:** Tecnológico de Costa Rica  
**Fecha de entrega:** Sábado 2 de Mayo de 2026  
**Grupo:** [Nombres del equipo]

---

## 📋 Descripción del Proyecto

Sistema de gestión para una clínica veterinaria, desarrollado como aplicación web Backend (REST API). Permite administrar propietarios, mascotas, veterinarios, consultas y medicamentos asociados a cada consulta.

El sistema fue diseñado aplicando principios de diseño de software (separación de responsabilidades, inyección de dependencias, arquitectura en capas) y desarrollado con Spring Boot 3.5.14.

---

## 🛠️ Tecnologías Utilizadas

| Tecnología | Versión | Uso |
|---|---|---|
| Java | 21 | Lenguaje principal |
| Spring Boot | 3.5.14 | Framework backend |
| Spring Data JPA | — | Persistencia de datos |
| PostgreSQL | — | Base de datos relacional |
| Docker | — | Contenedor de base de datos |
| Lombok | — | Reducción de boilerplate |
| Maven | — | Gestión de dependencias |
| Postman | — | Pruebas de endpoints |

---

## ⚙️ Configuración del Entorno de Desarrollo

Esta sección explica paso a paso cómo configurar el proyecto desde cero en una máquina nueva.

### Requisitos previos

- [IntelliJ IDEA](https://www.jetbrains.com/idea/) (Community o Ultimate)
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- [pgAdmin 4](https://www.pgadmin.org/download/) (cliente visual para PostgreSQL)
- Java 21 (se puede instalar desde IntelliJ al abrir el proyecto)

---

### 1. Crear el proyecto base con Spring Initializr

Spring Initializr es la herramienta oficial de Spring para generar proyectos base. En lugar de crear manualmente todas las carpetas, el `pom.xml` y la clase principal, esta herramienta genera todo automáticamente con las dependencias elegidas.

**Pasos:**

1. Ve a [https://start.spring.io](https://start.spring.io)
2. Configura las siguientes opciones:

| Campo | Valor |
|---|---|
| Project | Maven |
| Language | Java |
| Spring Boot | 3.5.14 |
| Group | com.veterinaria |
| Artifact | clinica |
| Packaging | Jar |
| Java | 21 |

3. Haz clic en **ADD DEPENDENCIES** y agrega:
    - Spring Web
    - Spring Data JPA
    - PostgreSQL Driver
    - Lombok
    - Validation

4. Clic en **GENERATE** — descarga un archivo `.zip`
5. Descomprime el zip en tu carpeta de trabajo

---

### 2. Abrir el proyecto en IntelliJ IDEA

1. Abre IntelliJ IDEA
2. **File → Open** → selecciona la carpeta del proyecto descomprimido
3. Si aparece el mensaje *"Trust and Open Project?"* → clic en **Trust Project**
4. Si aparece el mensaje *"Project JDK is not defined"* → clic en **Setup SDK** y selecciona Java 21. Si no aparece ninguno instalado, selecciona **Download JDK → Version 21 → Eclipse Temurin → Download**
5. Espera a que Maven descargue todas las dependencias (barra de progreso abajo a la derecha)

---

### 3. Crear la base de datos con Docker

Se utiliza Docker para correr PostgreSQL en un contenedor en lugar de instalarlo directamente en la máquina. Esto tiene varias ventajas:

- No requiere instalación de PostgreSQL en el sistema operativo
- La base de datos queda aislada y es fácil de eliminar o recrear
- Todos los integrantes del equipo usan exactamente la misma versión y configuración
- Se levanta con un solo comando

**Pasos:**

1. Asegúrate de que Docker Desktop esté corriendo (ícono de ballena en la barra de tareas)
2. Abre una terminal en IntelliJ: **View → Tool Windows → Terminal**
3. Ejecuta el siguiente comando:

```bash
docker run --name veterinaria-db -e POSTGRES_PASSWORD=admin123 -e POSTGRES_DB=veterinaria -p 5432:5432 -d postgres
```

**¿Qué hace este comando?**

| Parámetro | Significado |
|---|---|
| `--name veterinaria-db` | Nombre del contenedor |
| `-e POSTGRES_PASSWORD=admin123` | Contraseña del usuario postgres |
| `-e POSTGRES_DB=veterinaria` | Crea la base de datos `veterinaria` automáticamente |
| `-p 5432:5432` | Expone el puerto 5432 del contenedor al puerto 5432 de tu máquina |
| `-d postgres` | Usa la imagen oficial de PostgreSQL y corre en segundo plano |

Si el comando termina mostrando un hash largo (ejemplo: `383e47de30e6f5e12fd0ddf9b3dd834ba318...`), el contenedor fue creado exitosamente.

> **Nota importante:** La próxima vez que reinicies tu máquina, el contenedor no se levanta automáticamente. Para volver a iniciarlo ejecuta:
> ```bash
> docker start veterinaria-db
> ```

---

### 4. Conectar pgAdmin a la base de datos

pgAdmin es una herramienta visual para administrar bases de datos PostgreSQL. Se usa para verificar que las tablas se crearon correctamente cuando Spring Boot levanta, y para inspeccionar los datos durante el desarrollo.

**Pasos:**

1. Abre pgAdmin 4
2. Clic derecho en **Servers → Register → Server**
3. En la pestaña **General**:
    - Name: `Docker`
4. En la pestaña **Connection**:
    - Host: `localhost`
    - Port: `5432`
    - Username: `postgres`
    - Password: `admin123`
5. Clic en **Save**

Deberías ver la base de datos `veterinaria` dentro de **Servers → Docker → Databases**.

---

### 5. Configurar application.properties

Abre el archivo `src/main/resources/application.properties` y reemplaza su contenido con:

```properties
spring.application.name=clinica

spring.datasource.url=jdbc:postgresql://localhost:5432/veterinaria
spring.datasource.username=postgres
spring.datasource.password=admin123

spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8080
```

> **¿Qué hace `ddl-auto=update`?** Le indica a Hibernate que cree o actualice las tablas en la base de datos automáticamente cada vez que el proyecto levanta, basándose en las entidades JPA definidas en el código. No es necesario escribir SQL a mano para crear las tablas.

---

### 6. Correr el proyecto

1. Abre el archivo `ClinicaApplication.java` en `src/main/java/com/veterinaria/clinica/`
2. Clic en el botón ▶️ verde
3. En la consola debe aparecer: `Started ClinicaApplication in X seconds`

La API queda disponible en: `http://localhost:8080`

---

## 📁 Estructura del Proyecto

*(Se irá completando conforme avance el desarrollo)*

```
src/
└── main/
    ├── java/com/veterinaria/clinica/
    │   └── ClinicaApplication.java
    └── resources/
        └── application.properties
```

---

## 👥 Integrantes del Equipo

| Nombre            | Carné        |
|-------------------|--------------|
| [Kenneth Rojas Jimenez] | [2021466579] |
| [Nombre 2]        | [Carné]      |
| [Nombre 3]        | [Carné]      |
| [Nombre 4]        | [Carné]      |

---

*Proyecto desarrollado para el curso IC-6821 Diseño de Software — TEC, 2026*