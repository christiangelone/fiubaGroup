package fiubagroup

import grails.gorm.transactions.Transactional

@Transactional
class AlumnosService {

    def votarAlumno(Alumno alumno, Integer puntuacion) {
        alumno.puntuar(puntuacion).save()
    }

    def obtenerMateriasCursadas(Integer alumnoId){
        def alumno = Alumno.findById(alumnoId)
        return IntencionDeCursada.findAllByAlumno(alumno).collect{it.materia}
    }

    def obtenerAlumnos(Integer alumnoId, String codigoMateria) {
        def alumno = Alumno.findById(alumnoId)
		def materia = Materia.findByCodigo(codigoMateria)
        def grupo  = IntencionDeCursada.findAllWhere(alumno: alumno, materia: materia )?.first()?.grupo
        return grupo?.alumnos
    }
}
