<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>FiubaGroup</title>
</head>
<body>
  <h1>Mis intenciones de cursada</h1>

  <table style="width:100%; text-align: center; border: 1px solid black;">
    <tr style="border: #000 solid 1px;">
      <th style="border: 1px solid black;">Materia</th>
      <th style="border: 1px solid black;">Cuatrimeste</th>
      <th style="border: 1px solid black;">Grupo</th>
      <th style="border: 1px solid black;">Acciones</th>
    </tr>
    <g:each in="${intenciones}">
      <tr>
        <td style="border: 1px solid black;">Codigo: ${it.materia.codigo}</td>
        <td style="border: 1px solid black;">Año: ${it.cuatrimestre.anio} Número: ${it.cuatrimestre.numero}</td>
        <td style="border: 1px solid black;">
        <g:if test="${it.grupo != null}">
            <a href="/grupo/show/${it.grupo.id}">${it.grupo.nombre}</a>
        </g:if>
        </td>
        <td style="border: 1px solid black;">
          <g:if test="${it.grupo == null}">
            <a href="/grupo/armar?intencionDeFormarGrupoId=${it.id}">Armar grupo</a>
          </g:if>
        </td>
      </tr>
    </g:each>
  </table>

  <br/>
  <a href="javascript:history.back()">Volver</a>

</body>
</html>
