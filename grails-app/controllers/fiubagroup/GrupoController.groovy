package fiubagroup

class GrupoController {

    static scaffold = Grupo

    def armar(Integer formularioId) {

        def formulario = FormularioDeCursada.findById(formularioId)

        def alumnos = []
        def cuatrimestre = null
        def materia = null
        if(formulario != null){
            def service = new GrupoService()
            alumnos = service.proponerAlumnos(formulario)
            cuatrimestre = formulario.cuatrimestre
            materia = formulario.materia
        }

        [
            alumnos: alumnos,
            cuatrimestre: cuatrimestre,
            materia: materia,
            formularioId: formularioId,
            alumnosIdsStr: alumnos.inject("", { ids, alumno -> ids == "" ? alumno.nombre : ids + ",${alumno.nombre}" }),
            alumnosNombres: alumnos.collect { alumno -> alumno.nombre },
            grupos: [
                new Grupo(nombre: "grupo1", materia: null, cuatrimestre: null, alumnos: []),
                new Grupo(nombre: "grupo2", materia: null, cuatrimestre: null, alumnos: [])
            ]
        ]
    }

    def procesarArmado() {
        println(params)
        redirect(action: "armar")
    }

}
