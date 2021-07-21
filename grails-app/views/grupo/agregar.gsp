<%--
  Created by IntelliJ IDEA.
  User: contingencia
  Date: 21/07/2021
  Time: 19:16
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title></title>
</head>

<body>
<h1>Armar grupo</h1>

<h3>Agregar alumno al grupo</h3>
<br/>
<label>Alumnos:</label>
<ul>
	<g:each in="${alumnos}">
		<li><g:link class="procesarAgregado" action="procesarAgregado" params="${[alumnoId: it.id, grupoId: grupoId]}">${it.nombre}</g:link></li>
	</g:each>
</ul>
<br/>

<a href="/intencionDeFormarGrupo/listado">Volver</a>
</body>
</html>
