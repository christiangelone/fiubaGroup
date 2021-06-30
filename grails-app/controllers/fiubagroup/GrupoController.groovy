package fiubagroup

class GrupoController {

    static scaffold = Grupo

    def armar(Integer formularioId) {

        def formulario = FormularioDeCursada.findById(formularioId)

        def alumnos = []
        def cuatrimestre = null
        def materia = null
        def alumnoArmador = null
        if(formulario != null){
            def service = new GrupoService()
            alumnos = service.proponerAlumnos(formulario)
            cuatrimestre = formulario.cuatrimestre
            materia = formulario.materia
            alumnoArmador = formulario.alumno
        }

        [
            alumnoId: alumnoArmador.id,
            alumnos: alumnos,
            cuatrimestre: cuatrimestre,
            materia: materia,
            formularioId: formularioId,
            alumnosIdsStr: alumnos.inject("", { ids, alumno -> ids == "" ? alumno.id : ids + ",${alumno.id}" }),
            alumnosNombres: alumnos.collect { alumno -> alumno.nombre },
            grupos: [
                new Grupo(nombre: "grupo1", materia: null, cuatrimestre: null, alumnos: []),
                new Grupo(nombre: "grupo2", materia: null, cuatrimestre: null, alumnos: [])
            ]
        ]
    }

    def procesarArmado() {
        println(params)

        def materia = Materia.findById(params.materiaId)
        def cuatrimestre = Cuatrimestre.findById(params.cuatrimestreId)
        def ids = params.alumnoIds.split(',').collect{ Long.valueOf(it) }
        println(ids)
        println(ids.first().getClass())
        println(Alumno.list().first().id.getClass())
        println(Alumno.list())
        def alumnos = Alumno.list().findAll{ ids.contains(it.id) }
        println(alumnos)
        def grupo = new Grupo(
            nombre: params.nombre,
            cuatrimestre: cuatrimestre,
            materia: materia
        )

        for (alumno in alumnos) {
            grupo.addToAlumnos(alumno)
        }
        
        grupo.save()

        for (alumno in alumnos) {
            alumno
            def formularioDeCursada = FormularioDeCursada.findWhere(materia: materia, alumno: alumno, cuatrimestre: cuatrimestre)
            formularioDeCursada.grupo = grupo
            formularioDeCursada.save()
        }
       
        redirect(action: "listado", controller: "formularioDeCursada", params: [alumnoId: params["alumnoId"]])
    }

}
