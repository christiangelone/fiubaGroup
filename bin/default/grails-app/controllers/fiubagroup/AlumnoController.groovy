package fiubagroup

class AlumnoController {

    static scaffold = Alumno

    def alumnosService = new AlumnosService()
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
        redirect(action: "listado", controller: "formularioDeCursada", params: [alumnoId: params["alumnoId"]])
    }
}
