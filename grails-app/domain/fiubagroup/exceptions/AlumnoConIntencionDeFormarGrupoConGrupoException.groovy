package fiubagroup.exceptions

import fiubagroup.Alumno
import fiubagroup.Cuatrimestre
import fiubagroup.Materia

class AlumnoConIntencionDeFormarGrupoConGrupoException extends IllegalStateException {
	AlumnoConIntencionDeFormarGrupoConGrupoException(Alumno alumno, Materia materia, Cuatrimestre cuatrimestre) {
		super("el alumno ${alumno.nombre} ya tiene grupo " +
				"para la materia ${materia.codigo} " +
				"y cuatrimestre (año: ${cuatrimestre.anio}, ${cuatrimestre.numero})")
	}
}
