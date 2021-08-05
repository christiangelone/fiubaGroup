package fiubagroup

import grails.gorm.transactions.Transactional

@Transactional
class GrupoService {

	// buscar alumnos tengan afinidad con ese alumno solicitante del formulario recibido
	def proponerAlumnos(IntencionDeFormarGrupo intencionDeFormarGrupo) {
		def alumno = intencionDeFormarGrupo.alumno

		def intencionesDeFormarGrupoFiltrado = IntencionDeFormarGrupo.findAllWhere(
				materia: intencionDeFormarGrupo.materia,
				cuatrimestre: intencionDeFormarGrupo.cuatrimestre)

		def alumnosEnLaMismaCursada = intencionesDeFormarGrupoFiltrado.collect { it.alumno }

		def gruposDondeParticipo = Grupo.list().findAll { it.alumnos.contains(alumno) }

		def alumnosEnGruposPrevios = gruposDondeParticipo.collect { it.alumnos }.flatten().findAll { it != alumno }

		return alumno.alumnosAfines(alumnosEnLaMismaCursada, alumnosEnGruposPrevios)
	}

	private def proponerAlumnosEnBaseA(Grupo grupo, Alumno alumno) {
		def intencionDeFormarGrupo = IntencionDeFormarGrupo.findAllWhere(
				materia: grupo.materia,
				cuatrimestre: grupo.cuatrimestre,
				alumno: alumno).first()
		return proponerAlumnos(intencionDeFormarGrupo)
	}

	def proponerAlumnosFaltantes(Long grupoId, Long alumnoId) {
		def grupo = Grupo.findById(grupoId)
		def alumno = Alumno.findById(alumnoId)
		def alumnos = proponerAlumnosEnBaseA(grupo, alumno)
		alumnos.findAll {alumnoAFiltrar ->  alumnoAFiltrar.id != alumnoId && !grupo.pertence(alumnoAFiltrar) }
	}

	def armar(String nombre, String materiaId, String cuatrimestreId, List<Long> alumnoIds) {
		def materia = Materia.findById(materiaId)
		def cuatrimestre = Cuatrimestre.findById(cuatrimestreId)
		def alumnos = Alumno.list().findAll { alumnoIds.contains(it.id) }

		def grupo = new Grupo(
				nombre: nombre,
				cuatrimestre: cuatrimestre,
				materia: materia
		)
		grupo.agregar(alumnos)
		grupo.save()

		alumnos.each { alumno ->
			def intencionDeFormarGrupo = IntencionDeFormarGrupo.findWhere(materia: materia, alumno: alumno, cuatrimestre: cuatrimestre)
			intencionDeFormarGrupo.setGrupo(grupo)
		}
	}

	def abandonarGrupo(Long grupoId, Long alumnoDesertorId) {
		def grupo = Grupo.findById(grupoId)
		def alumno = Alumno.findById(alumnoDesertorId)
		grupo.removerAlumno(alumno)
		def intencionDeFormarGrupo = IntencionDeFormarGrupo.findWhere(materia: grupo.materia, alumno: alumno, cuatrimestre: grupo.cuatrimestre)
		intencionDeFormarGrupo.removerGrupo()
		return [grupo.cuatrimestre, grupo.materia]
	}

	def obtenerGrupos(Long alumnoId) {
		def alumno = Alumno.findById(alumnoId)
		return Grupo.list().findAll {
			alumno in it.alumnos
		}
	}

	def agregarAlumno(Long grupoId, Long alumnoId) {
		def alumno = Alumno.findById(alumnoId)
		def grupo = Grupo.findById(grupoId)
		grupo.agregar(alumno)
	}


	def obtenerAlumnosDelGrupo(Long grupoId, Long alumnoId) {
		def alumno = Alumno.findById(alumnoId)
		def grupo = Grupo.findById(grupoId)
		def alumnosFiltrados = grupo?.alumnos
		return alumnosFiltrados?.findAll { it != alumno }
	}
}
