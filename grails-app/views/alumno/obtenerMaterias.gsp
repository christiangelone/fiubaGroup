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
      <g:each in="${materias}">
      <li><g:link class="elegirAlumno" action="elegirAlumno" params="${[alumnoId: this.alumno?.id]}">${it.codigo}</g:link></li>
      </g:each>
    <a href="javascript:history.back()">Volver</a>
</body>
</html>
