<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="layout" content="main"/>
    <title>FiubaGroup</title>
</head>

<body>
<h1>Formar grupo</h1>

<div id="create-alumno" class="content scaffold-create" role="main">
    <g:if test="${!alumnos.isEmpty()}">
        <h3>Propuesta de grupo</h3>
        <br/>

        <form action="/grupo/procesarFormado" method="post" style="padding-left:20px;">
            <div class="fieldcontain">
                <label class="form" for="nombre">Nombre</label>
                <input type="text" id="nombre" name="nombre"/>
            </div>

            <div class="fieldcontain">
                <label>Materia: (Código:${materia.codigo})</label>
                <input type="hidden" id="materiaId" name="materiaId" value="${materia.id}"/>
            </div>

            <div class="fieldcontain">
                <label>Cuatrimestre: (Año: ${cuatrimestre.anio} Numero: ${cuatrimestre.numero})</label>
                <input type="hidden" id="cuatrimestreId" name="cuatrimestreId" value="${cuatrimestre.id}"/>
            </div>>

            <div class="fieldcontain">
                <label>Alumnos:</label>
                <ul>
                    <g:each in="${alumnosNombres}">
                        <li>${it}</li>
                    </g:each>
                </ul>
            </div>

            <input type="hidden" id="alumnoId" name="alumnoId" value="${alumnoId}"/>
            <input type="hidden" id="intencionDeFormarGrupoId" name="intencionDeFormarGrupoId"
                   value="${intencionDeFormarGrupoId}"/>
            <input type="hidden" id="alumnoIds" name="alumnoIds" value="${alumnosIdsStr}"/>
            <button class="buttons" type="submit">Formar grupo</button>
        </form>
    </g:if>

    <g:else>
        </h4>No tenemos una propuesta de alumnos para formar grupo</h4>
    </g:else>
</div>
<br/>

<h3>Grupos</h3>
<ul>
    <g:each in="${grupos}">
        <li>
            <a src="#">${it.nombre}</a>
        </li>
    </g:each>
</ul>

<div class="nav" role="navigation">
    <a class="home" href="/intencionDeFormarGrupo/listado">Volver<g:message></g:message></a>
</div>

</body>
</html>
