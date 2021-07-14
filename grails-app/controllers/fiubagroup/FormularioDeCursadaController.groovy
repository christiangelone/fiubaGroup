package fiubagroup

class FormularioDeCursadaController {

    static scaffold = IntencionDeCursada


    def listado(Integer alumnoId){
        def alumno = Alumno.findById(alumnoId)
        def formularios = IntencionDeCursada.findAllByAlumno(alumno)
        return [
            formularios: formularios
        ]
    }

}
