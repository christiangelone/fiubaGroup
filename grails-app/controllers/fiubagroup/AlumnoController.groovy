package fiubagroup

class AlumnoController {

    static scaffold = Alumno

	def alumnosService

    def obtenerMaterias(Integer alumnoId) {
        def alumno = Alumno.findById(alumnoId)
        def materias = alumnosService.obtenerMateriasCursadas(alumnoId)
        return [alumno: alumno, materias: materias]
    }

    def elegirAlumno(Integer alumnoId, String codigoMateria) {
        def alumno = Alumno.findById(alumnoId)
        def alumnos = alumnosService.obtenerAlumnos(alumnoId, codigoMateria)
        render([alumnos: alumnos, materias: materias])
    }

    def puntuar() {
        println(params)
        redirect(action: "listado", controller: "intencionesDeCursada", params: [alumnoId: params["alumnoId"]])
    }

	def puntuarAlumnos() {
		def alumnoPuntuadoId = Long.valueOf(params.alumnoPuntuadoId)
		def alumnoPuntuadorId = Long.valueOf(params.alumnoPuntuadorId)
		def grupoId = Long.valueOf(params.grupoId)
		def puntos = Integer.valueOf(params.puntuacion)
		def alumnoPuntuado
		try {
			alumnoPuntuado = alumnosService.puntuarAlumno(alumnoPuntuadoId, alumnoPuntuadorId,grupoId, puntos)
		} catch (Exception ignored) {
			redirect(action: "alumnoYaPuntuado", controller: "alumno", params: [
					alumnoPuntuadoId: alumnoPuntuadoId,
					alumnoPuntuadorId: alumnoPuntuadorId
			])
			return
		}

		[
				alumnoPuntuado: alumnoPuntuado
		]
	}

	def alumnoYaPuntuado(){
		def alumnoPuntuadoId = Long.valueOf(params.alumnoPuntuadoId)
		def alumnoPuntuadorId = Long.valueOf(params.alumnoPuntuadorId)
		[
				alumnoPuntuado: Alumno.findById(alumnoPuntuadoId),
				alumnoPuntuadorId: alumnoPuntuadorId
		]
	}

}
