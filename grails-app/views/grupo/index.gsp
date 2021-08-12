<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'grupo.label', default: 'Grupo')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-grupo" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                            default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-grupo" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <f:table collection="${grupoList}"/>

    <div class="pagination">
        <g:paginate total="${grupoCount ?: 0}"/>
    </div>
</div>

<div id="list-grupo-alumnos" class="content scaffold-list" role="main">
    <h1>Ver grupo por alumno</h1>
    <g:each in="${grupoList.collect { it.alumnos }.flatten().unique()}">
        <li>
            <a href="/grupo/listado?alumnoId=${it.id}" src="Alumno ">${it.nombre}</a>

        </li>
    </g:each>
</div>
</body>
</html>
