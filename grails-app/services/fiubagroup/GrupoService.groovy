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
}
