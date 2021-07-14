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
            chequearSiAlumnoTieneFormulariosDeCursada(alumno)
            chequearSiAlumnoTieneFormulariosDeCursadaSinGrupo(alumno)
            addToAlumnos(alumno)
        }
    }

    def agregarA(IntencionDeCursada formulario) {
        formulario.grupo = this
    }

    private def chequearSiAlumnoTieneFormulariosDeCursada(alumno){
        if(!alumno.tieneFormularioDeCursada(materia, cuatrimestre)) {
            throw new IllegalStateException(
                "el alumno ${alumno.nombre} no tiene formulariosDeCursada " +
                "para la materia ${materia.codigo} " +
                "y cuatrimestre (año: ${cuatrimestre.anio}, ${cuatrimestre.numero})"
            )
        }
    }

    private def chequearSiAlumnoTieneFormulariosDeCursadaSinGrupo(alumno){
        if(!alumno.tieneFormularioDeCursadaSinGrupo(materia, cuatrimestre)) {
            throw new IllegalStateException(
                "el alumno ${alumno.nombre} ya tiene grupo " +
                "para la materia ${materia.codigo} " +
                "y cuatrimestre (año: ${cuatrimestre.anio}, ${cuatrimestre.numero})"
            )
        }
    }
}
