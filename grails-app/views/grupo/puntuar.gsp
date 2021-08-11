<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="layout" content="main"/>
    <title>FiubaGroup</title>
</head>

<body>
<div id="punuat-alumno" class="content scaffold-create" role="main">
    <h1>Puntuado de  alumnos</h1>

    <g:if test="${!alumnos?.isEmpty()}">
        <div class="fieldcontain">

        <label>Materia: (Código:${materia.codigo})</label>
        </div>

        <div class="fieldcontain">
        <label>Cuatrimestre: (Año: ${cuatrimestre.anio} Numero: ${cuatrimestre.numero})</label>
        </div>

        <div class="fieldcontain">
        <label>Seleccione un alumno a puntuar</label>
        <input type="hidden" name="alumnoPuntuadorId" form="puntuacion" value="${alumnoPuntuadorId}">
        <input type="hidden" name="grupoId" form="puntuacion" value="${grupoId}">
        <select name="alumnoPuntuadoId" form="puntuacion">
            <g:each in="${alumnos}">
                <option value="${it.id}">${it.nombre}</option>
            </g:each>
        </select>
        </div>
        <div class="fieldcontain">
        <label>Seleccione una puntuacion entre 0 y 5</label>

        <select name="puntuacion" form="puntuacion">
            <g:each in="${0..5}">
                <option value="${it}">${it} puntos</option>
            </g:each>
        </select>
        </div>

        <form id="puntuacion" action="/alumno/puntuarAlumnos" method="post" style="padding-left:20px;">
            <button class="buttons" type="submit">Puntuar alumno</button>
        </form>
    </g:if>
    <g:else>
        </h4>No tienes alumnos disponibles para puntuar</h4>
    </g:else>
    <br/>
</div>

<div class="nav" role="navigation">
    <a class="home" href="/intencionDeFormarGrupo/listado">Volver<g:message></g:message></a>
</div>

</body>
</html>
