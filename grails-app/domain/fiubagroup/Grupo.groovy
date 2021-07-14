package fiubagroup

class Grupo {

    //static hasOne = [cuatrimestre: Cuatrimestre, materia: Materia]
    //static hasMany = [alumnos: Alumno]
    String nombre
    Cuatrimestre cuatrimestre
    Materia materia
    static hasMany = [alumnos: Alumno]

    static constraints = {
    }

    def agregar(List<Alumno> alumnos) {
        alumnos.each { alumno ->
            chequearSiAlumnoTieneIntencionDeFormarGrupo(alumno)
            chequearSiAlumnoTieneIntencionDeFormarGrupoSinGrupo(alumno)
            addToAlumnos(alumno)
        }
    }

    def agregarA(IntencionDeFormarGrupo intencionDeFormarGrupo) {
		intencionDeFormarGrupo.grupo = this
    }

    private def chequearSiAlumnoTieneIntencionDeFormarGrupo(alumno){
        if(!alumno.tieneIntencionDeFormarGrupo(materia, cuatrimestre)) {
            throw new IllegalStateException(
                "el alumno ${alumno.nombre} no tiene intencionDeFormarGrupo " +
                "para la materia ${materia.codigo} " +
                "y cuatrimestre (año: ${cuatrimestre.anio}, ${cuatrimestre.numero})"
            )
        }
    }

    private def chequearSiAlumnoTieneIntencionDeFormarGrupoSinGrupo(alumno){
        if(!alumno.tieneIntencionDeFormarGrupoSinGrupo(materia, cuatrimestre)) {
            throw new IllegalStateException(
                "el alumno ${alumno.nombre} ya tiene grupo " +
                "para la materia ${materia.codigo} " +
                "y cuatrimestre (año: ${cuatrimestre.anio}, ${cuatrimestre.numero})"
            )
        }
    }
}
