<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>FiubaGroup</title>
</head>
<body>
  <h1>ARMAR</h1>

  <g:if test="${!alumnosPropuesto.isEmpty()}">
    <form>
      <label for="nombre">Nombre</label>
      <input type="text" id="nombre" name="nombre" />
      <br/>
      <g:each in="${alumnosPropuesto}">
          <input type="checkbox" id="alumno-${it.nombre}" name="alumno-${it.nombre}" checked>
          <label for="alumno-${it.nombre}">${it.nombre}</label>
          <br/>
      </g:each>
      <button type="submit">Armar grupo</button>
    </form>
  </g:if>
  <g:else>
    </h4>No tenemos una propuesta de alumnos para armar grupo</h4>
  </g:else>

  <h3> Grupos </h3> 
  <ul>
    <g:each in="${grupos}">
      <li>
        <a src="#">${it.nombre}</a>
      </li>
    </g:each>
  </ul>

</body>
</html>