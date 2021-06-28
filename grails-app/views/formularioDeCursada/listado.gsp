<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>FiubaGroup</title>
</head>
<body>
  <h1>Mis Formularios</h1>

  <table style="width:100%; text-align: center;">
    <tr>
      <th>Materia</th>
      <th>Cuatrimeste</th>
      <th>Grupo</th>
      <th>Acciones</th>
    </tr>
    <g:each in="${formularios}">
      <tr>
        <td>Codigo: ${it.materia.codigo}</td>
        <td>Año: ${it.cuatrimestre.anio} Número: ${it.cuatrimestre.numero}</td>
        <td>
        <g:if test="${it.grupo != null}">
            <a href="/grupo/${it.grupo}">${it.grupo.nombre}</a>
        </g:if>
        </td>
        <td>
          <g:if test="${it.grupo == null}">
            <a href="/grupo/armar?formularioId=${it.id}">Armar grupo</a>
          </g:if>
        </td>
      </tr>
    </g:each>
  </table>

  <a href="javascript:history.back()">Volver</a>

</body>
</html>