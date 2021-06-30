<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>FiubaGroup</title>
</head>

<script>
  function getSelectValue()
  {
    document.getElementById("materiasSelect").
  }
 </script>

<body>
    <h1>Votacion de alumnos</h1>
	<g:select name="user.age" from="${materias}" value="${codigo}"  noSelection="['':'Elije la materia']">
      <g:each in="${materias}">
        <option value="${it.codigo}">${it.codigo}</option>
      </g:each>
    </g:select>

    <li><g:link class="elegirAlumno" action="elegirAlumno" params="${[alumnoId: this.alumno?.id]}"><g:message code="Elegir materia" /></g:link></li>
    <a href="javascript:history.back()">Volver</a>
</body>
</html>
