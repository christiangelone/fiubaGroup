package fiubagroup

import grails.gorm.transactions.Transactional

@Transactional
class GrupoService {

    // buscar alumnos tengan afinidad con ese alumno solicitante del formulario recibido
    def proponerAlumnos(IntencionDeFormarGrupo intencionDeFormarGrupo) {
        def alumno = intencionDeFormarGrupo.alumno

        def intencionDeFormarGrupoFiltrado = IntencionDeFormarGrupo.findAllWhere(
                                        materia: intencionDeFormarGrupo.materia,
                                        cuatrimestre: intencionDeFormarGrupo.cuatrimestre)

        def alumnosEnLaMismaCursada = intencionDeFormarGrupoFiltrado.collect{ it.alumno }

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
            def intencionDeFormarGrupo = IntencionDeFormarGrupo.findWhere(materia: materia, alumno: alumno, cuatrimestre: cuatrimestre)
            grupo.agregarA(intencionDeFormarGrupo)
            intencionDeFormarGrupo.save()
        }
    }
}
