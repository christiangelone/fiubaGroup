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

    def votar() {
        println(params)
        redirect(action: "listado", controller: "intencionesDeCursada", params: [alumnoId: params["alumnoId"]])
    }

	def votarAlumno() {
		println(params)
		def alumnoVotadoId = Long.valueOf(params.alumnoVotadoId)
		def puntuacion = Integer.valueOf(params.puntuacion)
		def alumnoVotado = Alumno.findById(alumnoVotadoId)
		alumnoVotado.puntuar(puntuacion)
		[
				alumnoVotado: alumnoVotado
		]
	}

}
