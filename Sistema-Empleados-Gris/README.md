# Sistema de Empleados

Aplicación CRUD sencilla hecha con Spring Boot, JSP, JPA y H2.

## Funciones

- Listar empleados.
- Registrar empleados.
- Editar empleados.
- Eliminar empleados con confirmación.
- Validar campos obligatorios, correo, salario y fecha.
- Guardar los datos localmente en `data/empleadosdb`.
- Adaptarse a computadora y celular.

## Requisito

- Java 17 o una versión superior.

No es necesario instalar Maven porque el proyecto incluye Maven Wrapper.

## Forma rápida de iniciar

1. Ejecuta `INICIAR_SISTEMA.bat`.
2. Espera a que la consola muestre `Started SistemaEmpleadosApplication`.
3. Abre `http://localhost:8080` en tu navegador.

La primera ejecución puede tardar unos minutos porque descarga las dependencias.

## Desde una terminal

```bat
mvnw.cmd spring-boot:run
```

Para crear el archivo WAR:

```bat
mvnw.cmd clean package
```

El resultado se genera en `target/sistema-empleados.war`.

## Base de datos

La aplicación usa H2 en modo archivo. Los datos permanecen guardados después de cerrar el sistema.

La consola opcional está en `http://localhost:8080/h2-console` con estos datos:

- JDBC URL: `jdbc:h2:file:./data/empleadosdb`
- Usuario: `sa`
- Contraseña: vacía
