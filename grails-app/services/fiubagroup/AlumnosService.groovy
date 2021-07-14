package fiubagroup

import grails.gorm.transactions.Transactional

@Transactional
class AlumnosService {

    def votarAlumno(Alumno alumno, Integer puntuacion) {
        alumno.puntuar(puntuacion).save()
    }

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
}
