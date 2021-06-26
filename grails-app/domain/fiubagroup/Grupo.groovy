package fiubagroup

class Grupo {

    //static hasOne = [cuatrimestre: Cuatrimestre, materia: Materia]
    //static hasMany = [alumnos: Alumno]
    Cuatrimestre cuatrimestre

    Materia materia
    static hasMany = [alumnos: Alumno]

    static constraints = {
    }
}
