package fiubagroup

class GrupoController {

    static scaffold = Grupo

    def grupoService

    def armar(Integer formularioId) {

        def formulario = FormularioDeCursada.findById(formularioId)

        def alumnos = []
        def cuatrimestre = null
        def materia = null
        def alumnoArmador = null
        if(formulario != null){
            alumnos = grupoService.proponerAlumnos(formulario)
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
        def materiaId = params.materiaId
        def cuatrimestreId = params.cuatrimestreId
        def nombre = params.nombre
        def ids = params.alumnoIds.split(',').collect{ Long.valueOf(it) }

        grupoService.armar(nombre, materiaId, cuatrimestreId, ids)

        redirect(action: "listado", controller: "formularioDeCursada", params: [alumnoId: params["alumnoId"]])
    }

}
