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
    <h1>Mis intenciones de formar grupo</h1>
    <table>
        <tr>
            <th>Materia</th>
            <th>Cuatrimeste</th>
            <th>Grupo</th>
            <th>Acciones</th>
        </tr>
        <g:each in="${intenciones}">
            <tr class="${(it.id % 2 == 0) ? 'odd' : 'even'}">
                <td>Codigo: ${it.materia.codigo}</td>
                <td>Año: ${it.cuatrimestre.anio} Número: ${it.cuatrimestre.numero}</td>
                <td>
                    <g:if test="${it.grupo != null}">
                        <a href="/grupo/show/${it.grupo.id}">${it.grupo.nombre}</a>
                    </g:if>
                </td>
                <td>
                    <g:if test="${it.grupo == null}">
                        <a href="/grupo/formar?intencionDeFormarGrupoId=${it.id}">Formar grupo</a>
                    </g:if>
                </td>
            </tr>
        </g:each>
    </table>
</div>

<div class="nav" role="navigation">
    <a class="home" href="/intencionDeFormarGrupo">Volver<g:message></g:message></a>
</div>

</body>
</html>
