package fiubagroup

import grails.gorm.transactions.Transactional

@Transactional
class AlumnosService {
    def obtenerMateriasCursadas(Integer alumnoId){
        def alumno = Alumno.findById(alumnoId)
        return IntencionDeFormarGrupo.findAllByAlumno(alumno).collect{it.materia}
    }

    def obtenerAlumnos(Integer alumnoId, String codigoMateria) {
        def alumno = Alumno.findById(alumnoId)
		def materia = Materia.findByCodigo(codigoMateria)
        def grupo  = IntencionDeFormarGrupo.findAllWhere(alumno: alumno, materia: materia )?.first()?.grupo
        return grupo?.alumnos
    }

	def puntuarAlumno(Long alumnoPuntuadoId, Long alumnoPuntuadorId, Long grupoId, Integer puntos){
		def alumnoPuntuado = Alumno.findById(alumnoPuntuadoId)
		def alumnoPuntuador = Alumno.findById(alumnoPuntuadorId)
		def grupo = Grupo.findById(grupoId)

		if (alumnoPuntuador.puedePuntuar(alumnoPuntuado, grupo)) {
			def puntuacion = new Puntuacion(
					grupo: grupo,
					alumnoPuntuador: alumnoPuntuador,
					alumnoPuntuado: alumnoPuntuado,
					puntos: puntos
			)
			puntuacion.save()
			alumnoPuntuador.puntuo(puntuacion)
			alumnoPuntuado.puntuar(puntuacion)
		} else{
			throw new Exception("Alumno ya puntuado")
		}

		if(grupo.todosPuntuaronA(alumnoPuntuado)){
			if(grupo.esPenalizable(alumnoPuntuado)){
				alumnoPuntuado.penalizar()
			}

			if(grupo.esPremiable(alumnoPuntuado)){
				alumnoPuntuado.premiar()
			}
		}

		return alumnoPuntuado
	}
}
