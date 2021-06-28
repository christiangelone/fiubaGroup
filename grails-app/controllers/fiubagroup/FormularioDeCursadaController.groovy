package fiubagroup

class FormularioDeCursadaController {

    static scaffold = FormularioDeCursada


    def listado(){
        def formularios = FormularioDeCursada.findAll()
        return [
            formularios: formularios
        ]
    }

}
