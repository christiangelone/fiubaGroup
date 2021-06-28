package fiubagroup

class FormularioDeCursadaController {

    static scaffold = FormularioDeCursada


    def listado(Integer alumnoId){
        def alumno = Alumno.findById(alumnoId)
        def formularios = FormularioDeCursada.findAllByAlumno(alumno)
        return [
            formularios: formularios
        ]
    }

}
