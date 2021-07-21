<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>FiubaGroup</title>
</head>

<body>
<h1>Mis grupos</h1>

<table style="width:100%; text-align: center; border: 1px solid black;">
	<tr style="border: #000 solid 1px;">
		<th style="border: 1px solid black;">Nombre</th>
		<th style="border: 1px solid black;">Materia</th>
		<th style="border: 1px solid black;">Cuatrimeste</th>
		<th style="border: 1px solid black;">Acciones</th>
	</tr>
	<g:each in="${grupos}">
		<tr>
			<td style="border: 1px solid black;">Nombre: ${it.nombre}</td>
			<td style="border: 1px solid black;">Materia: ${it.materia.codigo}</td>
			<td style="border: 1px solid black;">Año: ${it.cuatrimestre.anio} Número: ${it.cuatrimestre.numero}</td>
			<td style="border: 1px solid black;">
				<ul>
					<li>
						<g:if test="${it.esVotables()}">
							<a href="/grupo/votar?grupoId=${it.id}&alumnoVotanteId=${alumnoId}">Votar alumnos</a>
						</g:if>
					</li>
					<li>

						<a href="/grupo/abandonar?grupoId=${it.id}&alumnoDesertorId=${alumnoId}">Abandonar grupo</a>

				</li>
					<li>
						<g:if test="${it.tieneDesertores()}">
							<a href="/grupo/agregar?grupoId=${it.id}&alumnoId=${alumnoId}">Agregar alumno</a>
						</g:if>
					</li>
				</ul>

			</td>
		</tr>
	</g:each>
</table>

<br/>
<a href="javascript:history.back()">Volver</a>

</body>
</html>
