package fiubagroup

class FormularioDeCursada {
    //static hasOne = [alumno: Alumno, materia: Materia, cuatrimestre:]
    Alumno alumno
    Materia materia
    Cuatrimestre cuatrimestre
    Grupo grupo

    static constraints = {
        grupo nullable: true
    }
}
