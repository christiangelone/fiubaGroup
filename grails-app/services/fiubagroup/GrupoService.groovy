package fiubagroup

import grails.gorm.transactions.Transactional

@Transactional
class GrupoService {

    // buscar alumnos tengan afinidad con ese alumno solicitante del formulario recibido
    def proponerAlumnos(FormularioDeCursada formularioDeCursada) {
        def alumno = formularioDeCursada.alumno

        def formulariosFiltrados = FormularioDeCursada.findAllWhere(
                                        materia: formularioDeCursada.materia, 
                                        cuatrimestre: formularioDeCursada.cuatrimestre)
        
        def alumnosEnLaMismaCursada = formulariosFiltrados.collect{ it.alumno }

        def gruposDondeParticipo = Grupo.list().findAll { it.alumnos.contains(alumno) }

        def alumnosEnGruposPrevios = gruposDondeParticipo.collect{ it.alumnos }.flatten().findAll{ it != alumno }

        return alumno.alumnosAfines(alumnosEnLaMismaCursada, alumnosEnGruposPrevios)
    }

    def armar(String nombre, String materiaId, String cuatrimestreId, List<Long> alumnoIds) {
        def materia = Materia.findById(materiaId)
        def cuatrimestre = Cuatrimestre.findById(cuatrimestreId)
        def alumnos = Alumno.list().findAll{ alumnoIds.contains(it.id) }

        def grupo = new Grupo(
            nombre: nombre,
            cuatrimestre: cuatrimestre,
            materia: materia
        )
        grupo.agregar(alumnos)
        grupo.save()
        
        alumnos.each { alumno ->
            def formularioDeCursada = FormularioDeCursada.findWhere(materia: materia, alumno: alumno, cuatrimestre: cuatrimestre)
            grupo.agregarA(formularioDeCursada) 
            formularioDeCursada.save()
        }
    }
}
