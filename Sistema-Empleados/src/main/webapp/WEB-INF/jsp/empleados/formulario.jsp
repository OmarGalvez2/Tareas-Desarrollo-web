<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${empty empleado.id ? 'Nuevo empleado' : 'Editar empleado'}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
<div class="pagina">
    <aside class="barra-lateral">
        <div class="marca">
            <span class="marca-icono">RH</span>
            <div>
                <strong>Personal</strong>
                <small>Administración</small>
            </div>
        </div>

        <nav class="menu">
            <a class="menu-enlace" href="${pageContext.request.contextPath}/empleados">
                <span class="menu-punto"></span> Empleados
            </a>
            <a class="menu-enlace activo" href="${pageContext.request.contextPath}/empleados/nuevo">
                <span class="menu-simbolo">+</span> ${empty empleado.id ? 'Nuevo registro' : 'Editar registro'}
            </a>
        </nav>

        <div class="ayuda-lateral">
            <strong>Información básica</strong>
            <p>Los campos marcados con * son obligatorios.</p>
        </div>

        <p class="barra-pie">Sistema local · Spring Boot</p>
    </aside>

    <main class="contenido contenido-formulario">
        <a class="volver" href="${pageContext.request.contextPath}/empleados">← Volver a empleados</a>

        <header class="encabezado encabezado-formulario">
            <div>
                <p class="subtitulo">Datos del personal</p>
                <h1>${empty empleado.id ? 'Nuevo empleado' : 'Editar empleado'}</h1>
                <p>Completa la información y guarda los cambios.</p>
            </div>
        </header>

        <section class="tarjeta formulario-tarjeta">
            <form:form method="post"
                       action="${pageContext.request.contextPath}/empleados/guardar"
                       modelAttribute="empleado">
                <form:hidden path="id"/>

                <div class="form-grid">
                    <div class="campo">
                        <form:label path="nombre">Nombre *</form:label>
                        <form:input path="nombre" maxlength="60" placeholder="Ej. Ana"/>
                        <form:errors path="nombre" cssClass="error-campo"/>
                    </div>

                    <div class="campo">
                        <form:label path="apellido">Apellido *</form:label>
                        <form:input path="apellido" maxlength="60" placeholder="Ej. Pérez"/>
                        <form:errors path="apellido" cssClass="error-campo"/>
                    </div>

                    <div class="campo">
                        <form:label path="email">Correo electrónico *</form:label>
                        <form:input path="email" type="email" maxlength="120" placeholder="nombre@correo.com"/>
                        <form:errors path="email" cssClass="error-campo"/>
                    </div>

                    <div class="campo">
                        <form:label path="telefono">Teléfono</form:label>
                        <form:input path="telefono" maxlength="25" placeholder="Ej. 70000000"/>
                        <form:errors path="telefono" cssClass="error-campo"/>
                    </div>

                    <div class="campo">
                        <form:label path="cargo">Cargo *</form:label>
                        <form:input path="cargo" maxlength="80" placeholder="Ej. Contador"/>
                        <form:errors path="cargo" cssClass="error-campo"/>
                    </div>

                    <div class="campo">
                        <form:label path="salario">Salario *</form:label>
                        <form:input path="salario" type="number" min="0" step="0.01" placeholder="0.00"/>
                        <form:errors path="salario" cssClass="error-campo"/>
                    </div>

                    <div class="campo">
                        <form:label path="fechaIngreso">Fecha de ingreso *</form:label>
                        <form:input path="fechaIngreso" type="date"/>
                        <form:errors path="fechaIngreso" cssClass="error-campo"/>
                    </div>

                    <div class="campo campo-checkbox">
                        <form:checkbox path="activo"/>
                        <form:label path="activo">Empleado activo</form:label>
                    </div>
                </div>

                <div class="form-acciones">
                    <a class="boton boton-secundario" href="${pageContext.request.contextPath}/empleados">Cancelar</a>
                    <button class="boton boton-principal" type="submit">Guardar empleado</button>
                </div>
            </form:form>
        </section>
    </main>
</div>
</body>
</html>
