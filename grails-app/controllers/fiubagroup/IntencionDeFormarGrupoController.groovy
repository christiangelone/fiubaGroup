package fiubagroup

class IntencionDeFormarGrupoController {

	static scaffold = IntencionDeFormarGrupo


	def listado(Integer alumnoId) {
		def alumno = Alumno.findById(alumnoId)
		def intenciones = IntencionDeFormarGrupo.findAllByAlumno(alumno)
		return [
				intenciones: intenciones
		]
	}

}
