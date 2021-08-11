<%--
  Created by IntelliJ IDEA.
  User: contingencia
  Date: 21/07/2021
  Time: 19:16
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>FiubaGroup</title>
</head>

<body>
<div id="content" role="main">
    <h1>Formar grupo</h1>

    <h3>Agregar alumno al grupo</h3>

    <div class="fieldcontain">
        <label>Alumnos:</label>
        <ul>
            <g:each in="${alumnos}">
                <li><g:link class="procesarAgregarAlumno" action="procesarAgregarAlumno"
                            params="${[alumnoId: it.id, grupoId: grupoId]}">${it.nombre}</g:link></li>
            </g:each>
        </ul>
    </div>
</div>

<div class="nav" role="navigation">
    <a class="home" href="/intencionDeFormarGrupo/listado">Volver<g:message></g:message></a>
</div>
</body>
</html>
