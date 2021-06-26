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
        
        def alumnosEnLaMismaCursada = formulariosFiltrados.map{ it.alumno}

        def gruposDondeParticipo = Grupos.list().findAll { it.alumnos.contain(alumno) }

        def alumnosEnGruposPrevios = gruposDondeParticipo.map{ it.alumnos }.flatten().filter{ it != alumno }

        alumno.alumnosAfines(alumnosEnLaMismaCursada, alumnosEnGruposPrevios) 
    }
}
