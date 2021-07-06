<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>FiubaGroup</title>
</head>
<body>
    <h1>Votacion de alumnos</h1>
    <h1>Selecciona una materia</h1>
    <br>
      <g:each in="${materias}">
        <option value="${it.codigo}">it.codigo</option>
      </g:each>
    </br>
    
    <li><g:link class="obtenerMaterias" action="votar"><g:message code="Votar" args="[entityName]" /></g:link></li>
    <a href="javascript:history.back()">Volver</a>
</body>
</html>