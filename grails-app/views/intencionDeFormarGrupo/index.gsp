<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'intencionDeFormarGrupo.label', default: 'IntencionDeFormarGrupo')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-intencionDeFormarGrupo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-intencionDeFormarGrupo" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${intencionDeFormarGrupoList}" />

            <div class="pagination">
                <g:paginate total="${intencionDeFormarGrupoCount ?: 0}" />
            </div>
        </div>
    <div id="list-intencionDeFormarGrupox" class="content scaffold-list" role="main">
        <h1>Ver listado de intenciones de formar grupo por alumno</h1>
        <g:each in="${intencionDeFormarGrupoList.collect{ it.alumno }.unique()}">
            <li>
                <a href="/intencionDeFormarGrupo/listado?alumnoId=${it.id}"  src="Alumno ">${it.nombre }</a>

            </li>
        </g:each>
    </div>
    </body>
</html>
