# VerdeApp - APP (SENA 2026)

Proyecto desarrollado para la gestión de residuos en conjuntos residenciales, cumpliendo con las 3 fases de transferencia de conocimiento (JPA, Validaciones/Excepciones y Security).

## Requisitos para ejecución
1. Tener instalado **MySQL**.
2. Crear la base de datos: `CREATE DATABASE verde_app_spring;`.
3. Configurar usuario/contraseña en `src/main/resources/application.properties`.

## Seguridad
El sistema cuenta con autenticación básica. Credenciales:
- **Admin:** `admin` / `admin123` (Permisos totales).
- **User:** `user` / `12345` (Solo consulta GET).


## Guía de Uso y Flujo de Datos (Importante)

Para que el sistema funcione correctamente y no arroje errores de integridad (500), se debe seguir este orden estrictamente:

1. **Crear el Conjunto:** Es la base de todo. Sin un conjunto con ID, no hay dónde ubicar las unidades (torre y apto).
2. **Crear la Unidad:** Se debe asociar al ID del conjunto creado en el paso 1.
3. **Crear el Residente:** Se asocia al ID de la unidad creada en el paso 2.

---

### Estructuras JSON para Postman

#### Paso 1: Crear Conjunto (POST)
**URL:** `http://localhost:8080/api/conjuntos`
```json
{
    "nombre": "VerdeApp Horizonte",
    "direccion": "Calle 170 # 15-40",
    "nit": "900.555.222-1"
}
```
#### Paso 2: Crear Unidad (POST)
**URL:** `http://localhost:8080/api/unidades`
```json
{
    "torre": "Torre 5",
    "numeroApartamento": "1002",
    "conjunto": { "conjuntoId": 1 }
}
```

#### Paso 3: Crear Residente (POST)
**URL:** `http://localhost:8080/residentes`
```json
{
  "nombre": "Yilmer",
  "primerApellido": "Gómez",
  "segundoApellido": "Sánchez",
  "cedula": "10102020",
  "email": "yilmer.adso@sena.edu.co",
  "unidad": { "unidadId": 1 }
}
```

## Seguridad y Roles
El sistema utiliza Basic Auth. Configura el usuario en la pestaña Authorization de Postman:

| Rol   | Usuario | Contraseña | Permisos                      |
|:------|:-------:|-----------:|:------------------------------|
| Admin |  admin  |   admin123 | Control total (CRUD completo) |
| User  |  user   |      12345 | Solo lectura (GET)            |
| OTROS |   N/A   |        N/A | Solo ver Unidades (Público).          |

**OJO: ¿Qué pasa si un usuario no tiene permiso?**
- Si un USER intenta un POST, PUT o DELETE, recibirá un error 403 Forbidden.

- Si no se ingresan credenciales, el sistema dará 401 Unauthorized, excepto en el listado de unidades.


## Validaciones de negocio
Se implementaron las siguientes validaciones:
- No escribir el nombre en un solo registro, sino separarlo para seguir la **atomicidad** (nombre, primer y segundo apellido).
- **Formato:** El email debe contener un formato válido (ej. usuario@dominio.com).

- **Obligatoriedad:** Campos como NIT, Cédula y Nombres no pueden enviarse vacíos (@NotBlank).

- **Unicidad:** - No se permiten dos residentes con la misma cédula.

- **Regla de Oro:** Una unidad (Torre/Apto) solo puede tener un residente responsable asignado (@OneToOne). Si intentas asignar un segundo residente a la misma unidad, el sistema lanzará un error de validación.
---

### Cómo abrir y correr este proyecto (Paso a Paso)

1. ***Clonar o Descargar***
   1. **Opción A (Git):** En IntelliJ, ve a File > New > Project from Version Control. Pega el enlace de este repositorio de GitHub y dale a Clone.
    2. **Opción B (ZIP):** Si descargaste el archivo .zip de GitHub, descomprímelo y en IntelliJ dale a File > Open y selecciona la carpeta VerdeApp.
2. ***Sincronizar las Librerías (Maven)***
Al abrir el proyecto, IntelliJ verá que faltan librerías (Security, JPA, etc.).
    1. En la esquina derecha de la pantalla, verás un icono pequeño con una "M" (Maven). Haz clic ahí y luego dale al icono de las dos flechas en círculo (Reload All Maven Projects).
    2. **¿Por qué?** Esto descarga automáticamente todo lo que el proyecto necesita para funcionar sin que tú tengas que instalar nada manualmente.
3. ***Configurar tu Base de Datos Local***
   Tu base de datos de MySQL probablemente tiene una contraseña diferente a la mía.
    1. Abre el archivo `src/main/resources/application.properties`
    2. Busca la línea `spring.datasource.password=`
   3. **Si tu MySQL NO tiene contraseña:** Borra lo que haya y déjalo vacío ( así: `src/main/resources/application.properties.`)
   4. **Si tu MySQL tiene contraseña:** Escríbela ahí (ej:`spring.datasource.password=tu-contraseña`)
4. ***Crear la Base de Datos:***
Antes de darle Play, ve a MySQL Workbench y ejecuta este comando único: `CREATE DATABASE verde_app_spring;`
    - No necesitas crear las tablas. Gracias a la configuración del proyecto, **Spring Boot creará las tablas por ti** la primera vez que lo corras.


## Solución de Problemas Comunes
1. **Error: Port 8080 already in use:** Significa que ya tienes otro programa usando ese puerto. Ve al archivo application.properties y cambia la línea a server.port=8081.

2. **Símbolos en rojo en el código:**
Si ves letras rojas en las clases (como @NotBlank o @Entity), es porque Maven no ha terminado de descargar las librerías. Repite el Paso 2.

3. **Error 401 Unauthorized en Postman:**
Recuerda que el proyecto tiene seguridad. En Postman, ve a la pestaña Authorization, elige Basic Auth y usa el usuario admin con la contraseña admin123.