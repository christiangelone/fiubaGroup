package fiubagroup.exceptions

import fiubagroup.Alumno
import fiubagroup.Cuatrimestre
import fiubagroup.Materia

class AlumnoSinIntencionDeFormarGrupoException extends IllegalStateException {
	AlumnoSinIntencionDeFormarGrupoException(Alumno alumno, Materia materia, Cuatrimestre cuatrimestre) {
		super("el alumno ${alumno.nombre} no tiene intencionDeFormarGrupo " +
				"para la materia ${materia.codigo} " +
				"y cuatrimestre (año: ${cuatrimestre.anio}, ${cuatrimestre.numero})")
	}
}

