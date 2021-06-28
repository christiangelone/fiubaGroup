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
    <form action="/grupo/procesarArmado" method="post" style="padding-left:20px;">
      <label for="nombre">Nombre</label>
      <input type="text" id="nombre" name="nombre" />
      <br/>
      <label>Materia: (Código:${materia.codigo})</label>
      <input type="hidden" id="materiaId" name="materiaId" value="${materia.id}" />
      <br/>
      <label>Cuatrimestre: (Año: ${cuatrimestre.anio} Numero: ${cuatrimestre.numero})</label>
      <input type="hidden" id="cuatrimestreId" name="cuatrimestreId" value="${cuatrimestre.id}" />
      <br/>
      <label>Alumnos:</label>
      <ul>
      <g:each in="${alumnosNombres}">
        <li>${it}</li>
      </g:each>
      </ul>
      <input type="hidden" id="formularioId" name="formularioId" value="${formularioId}" />
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

  <a href="/formularioDeCursada/listado">Volver</a>

</body>
</html>