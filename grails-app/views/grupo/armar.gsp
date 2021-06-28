<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>FiubaGroup</title>
</head>
<body>
  <h1>Armar grupo</h1>

  <g:if test="${!alumnos.isEmpty()}">
    <h3>Propuesta de grupo</h3>
    <br/>
    <form style="padding-left:20px;">
      <label for="nombre">Nombre</label>
      <input type="text" id="nombre" name="nombre" />
      <br/>
      <ul>
      <g:each in="${alumnosNombres}">
          <li>${it}</li>
      </g:each>
      </ul>
      <input type="hidden" id="alumnoIds" name="alumnoIds" value="${alumnosIdsStr}" />
      <button type="submit">Armar grupo</button>
    </form>
  </g:if>
  <g:else>
    </h4>No tenemos una propuesta de alumnos para armar grupo</h4>
  </g:else>
  <br/>
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