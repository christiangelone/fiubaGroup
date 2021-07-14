package fiubagroup

class IntencionDeFormarGrupo {
    //static hasOne = [alumno: Alumno, materia: Materia, cuatrimestre:]
    Materia materia
    Cuatrimestre cuatrimestre
    Grupo grupo

    static belongsTo = [alumno: Alumno]

    static constraints = {
        grupo nullable: true
    }

    def tieneCuatrimestreYMateria(Materia unaMateria, Cuatrimestre unCuatrimestre){
        return materia.equals(unaMateria) && cuatrimestre.equals(unCuatrimestre)
    }

    def tieneGrupo(){
        return grupo != null
    }
}
