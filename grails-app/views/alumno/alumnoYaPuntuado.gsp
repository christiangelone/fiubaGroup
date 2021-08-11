<%--
  Created by IntelliJ IDEA.
  User: contingencia
  Date: 28/07/2021
  Time: 20:11
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>FiubaGroup</title>
</head>

<body>
<div id="content" role="main">
    <section class="row colset-2-its">
        <h1>Puntuado de  alumnos</h1>
        <h3>Ya puntuaste a ${alumnoPuntuado.nombre}</h3>
    </section>
</div>

<div class="nav" role="navigation">
    <div class="nav" role="navigation">
        <a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </div>
</div>
</body>
</html>
