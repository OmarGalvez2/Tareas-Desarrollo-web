<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Empleados</title>
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
            <a class="menu-enlace activo" href="${pageContext.request.contextPath}/empleados">
                <span class="menu-punto"></span> Empleados
            </a>
            <a class="menu-enlace" href="${pageContext.request.contextPath}/empleados/nuevo">
                <span class="menu-simbolo">+</span> Nuevo registro
            </a>
        </nav>

        <div class="resumen-lateral">
            <span>Total de empleados</span>
            <strong>${fn:length(empleados)}</strong>
        </div>

        <p class="barra-pie">Sistema local · Spring Boot</p>
    </aside>

    <main class="contenido">
        <header class="encabezado">
            <div>
                <p class="subtitulo">Directorio del personal</p>
                <h1>Empleados</h1>
                <p>Consulta y administra la información de cada integrante.</p>
            </div>
            <a class="boton boton-principal" href="${pageContext.request.contextPath}/empleados/nuevo">
                + Nuevo empleado
            </a>
        </header>

        <c:if test="${not empty mensaje}">
            <div class="alerta alerta-exito"><c:out value="${mensaje}"/></div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alerta alerta-error"><c:out value="${error}"/></div>
        </c:if>

        <c:choose>
            <c:when test="${empty empleados}">
                <section class="tarjeta vacio">
                    <span class="vacio-icono">RH</span>
                    <h2>Todavía no hay empleados</h2>
                    <p>Registra al primer integrante para comenzar.</p>
                    <a class="boton boton-principal" href="${pageContext.request.contextPath}/empleados/nuevo">
                        Crear primer empleado
                    </a>
                </section>
            </c:when>
            <c:otherwise>
                <section class="empleados-grid">
                    <c:forEach items="${empleados}" var="empleado">
                        <article class="tarjeta empleado-card">
                            <div class="empleado-cabecera">
                                <div class="identidad">
                                    <span class="avatar">
                                        <c:out value="${fn:substring(empleado.nombre, 0, 1)}"/>
                                        <c:out value="${fn:substring(empleado.apellido, 0, 1)}"/>
                                    </span>
                                    <div>
                                        <h2><c:out value="${empleado.nombre} ${empleado.apellido}"/></h2>
                                        <p><c:out value="${empleado.cargo}"/></p>
                                    </div>
                                </div>
                                <span class="estado ${empleado.activo ? 'estado-activo' : 'estado-inactivo'}">
                                    ${empleado.activo ? 'Activo' : 'Inactivo'}
                                </span>
                            </div>

                            <dl class="datos-empleado">
                                <div>
                                    <dt>Correo</dt>
                                    <dd><c:out value="${empleado.email}"/></dd>
                                </div>
                                <div>
                                    <dt>Teléfono</dt>
                                    <dd><c:out value="${empty empleado.telefono ? 'Sin registrar' : empleado.telefono}"/></dd>
                                </div>
                                <div>
                                    <dt>Salario</dt>
                                    <dd><fmt:formatNumber value="${empleado.salario}" minFractionDigits="2" maxFractionDigits="2"/></dd>
                                </div>
                                <div>
                                    <dt>Fecha de ingreso</dt>
                                    <dd><c:out value="${empleado.fechaIngreso}"/></dd>
                                </div>
                            </dl>

                            <div class="acciones">
                                <a class="boton boton-secundario"
                                   href="${pageContext.request.contextPath}/empleados/editar/${empleado.id}">
                                    Editar
                                </a>
                                <form action="${pageContext.request.contextPath}/empleados/eliminar/${empleado.id}"
                                      method="post"
                                      onsubmit="return confirm('¿Deseas eliminar este empleado?');">
                                    <button class="boton boton-eliminar" type="submit">Eliminar</button>
                                </form>
                            </div>
                        </article>
                    </c:forEach>
                </section>
            </c:otherwise>
        </c:choose>
    </main>
</div>
</body>
</html>
