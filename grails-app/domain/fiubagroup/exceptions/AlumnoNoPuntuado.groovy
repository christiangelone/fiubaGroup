package fiubagroup.exceptions

import fiubagroup.Alumno
import fiubagroup.Grupo

class AlumnoNoPuntuado extends IllegalStateException {
	AlumnoNoPuntuado(Grupo grupo, Alumno alumno) {
		super("El alumno ${alumno.nombre} no tiene puntos para el grupo ${grupo.nombre}")
	}
}
