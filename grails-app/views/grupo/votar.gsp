<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>FiubaGroup</title>
</head>

<body>
<h1>Votado de  alumnos</h1>

<g:if test="${!alumnos?.isEmpty()}">
	<br/>

	<label>Materia: (Código:${materia.codigo})</label>
	<br/>
	<label>Cuatrimestre: (Año: ${cuatrimestre.anio} Numero: ${cuatrimestre.numero})</label>
	<br/>
	<br/>
	<label>Seleccione un alumno a votar</label>
	<br/>
	<select id="alumnoVotadoId" name="alumnoVotadoId" form="votacion">
		<g:each in="${alumnos}">
			<option value="${it.id}">${it.nombre}</option>
		</g:each>
	</select>
	<br/>
	<br/>
	<label>Seleccione una puntuacion entre 0 y 5</label>
	<br/>
	<select id="puntuacion" name="puntuacion" form="votacion">
		<g:each in="${0..5}">
			<option value="${it}">${it} puntos</option>
		</g:each>
	</select>
	<br/>
	<br/>
	<br/>

	<form id="votacion" action="/alumno/votarAlumno" method="post" style="padding-left:20px;">
		<button type="submit">Votar alumno</button>
	</form>
</g:if>
<g:else>
	</h4>No tienes alumnos disponibles para votar</h4>
</g:else>
<br/>

<a href="/intencionDeFormarGrupo/listado">Volver</a>

</body>
</html>
