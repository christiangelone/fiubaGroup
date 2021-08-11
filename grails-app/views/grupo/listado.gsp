<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="layout" content="main"/>
    <title>FiubaGroup</title>
</head>

<body>

<div id="list-intenciones" class="content scaffold-list" role="main">
    <h1>Mis grupos</h1>
    <table>
        <tr>
            <th>Nombre</th>
            <th>Materia</th>
            <th>Cuatrimeste</th>
            <th>Acciones</th>
        </tr>
        <g:each in="${grupos}">
            <tr class="${(it.id % 2 == 0) ? 'odd' : 'even'}">
                <td>Nombre: ${it.nombre}</td>
                <td>Materia: ${it.materia.codigo}</td>
                <td>Año: ${it.cuatrimestre.anio} Número: ${it.cuatrimestre.numero}</td>
                <td>
                    <ul>
                        <li>
                            <g:if test="${it.esVotables()}">
                                <a href="/grupo/puntuar?grupoId=${it.id}&alumnoPuntuadorId=${alumnoId}">Votar alumnos</a>
                            </g:if>
                        </li>
                        <li>

                            <a href="/grupo/abandonar?grupoId=${it.id}&alumnoDesertorId=${alumnoId}">Abandonar grupo</a>

                        </li>
                        <g:if test="${it.tieneDesertores()}">
                            <li>
                                <a href="/grupo/agregar?grupoId=${it.id}&alumnoId=${alumnoId}">Agregar alumno</a>
                            </li>
                        </g:if>
                    </ul>

                </td>
            </tr>
        </g:each>
    </table>
</div>
<br/>

<div class="nav" role="navigation">
    <a class="home" href="javascript:history.back()">Volver<g:message></g:message></a>
</div>
</body>
</html>
