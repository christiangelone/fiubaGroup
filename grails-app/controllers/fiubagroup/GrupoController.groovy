package fiubagroup

class GrupoController {

	static scaffold = Grupo

	def grupoService

	def armar(Integer intencionDeFormarGrupoId) {

		def intencionesDeFormarGrupo = IntencionDeFormarGrupo.findById(intencionDeFormarGrupoId)

		def alumnos = []
		def cuatrimestre = null
		def materia = null
		def alumnoArmador = null
		if (intencionesDeFormarGrupo != null) {
			alumnos = grupoService.proponerAlumnos(intencionesDeFormarGrupo)
			cuatrimestre = intencionesDeFormarGrupo.cuatrimestre
			materia = intencionesDeFormarGrupo.materia
			alumnoArmador = intencionesDeFormarGrupo.alumno
		}

		[
				alumnoId              : alumnoArmador.id,
				alumnos               : alumnos,
				cuatrimestre          : cuatrimestre,
				materia               : materia,
				intencionDeFormarGrupo: intencionDeFormarGrupoId,
				alumnosIdsStr         : alumnos.inject("", { ids, alumno -> ids == "" ? alumno.id : ids + ",${alumno.id}" }),
				alumnosNombres        : alumnos.collect { alumno -> alumno.nombre },
				grupos                : [
						new Grupo(nombre: "grupo1", materia: null, cuatrimestre: null, alumnos: []),
						new Grupo(nombre: "grupo2", materia: null, cuatrimestre: null, alumnos: [])
				]
		]
	}

	def listado(Long alumnoId) {
		def grupos = grupoService.obtenerGrupos(alumnoId)
		return [
				grupos  : grupos,
				alumnoId: alumnoId
		]
	}


	def votar(Long grupoId, Long alumnoVotanteId) {
		def grupo = Grupo.findById(grupoId)

		def alumnos = grupoService.obtenerAlumnosDelGrupo(grupoId, alumnoVotanteId) ?: []

		def cuatrimestre = grupo?.cuatrimestre
		def materia = grupo?.materia

		[
				alumnoId    : alumnoVotanteId,
				alumnos     : alumnos,
				cuatrimestre: cuatrimestre,
				materia     : materia
		]
	}

	def abandonar(Long grupoId, Long alumnoDesertorId) {
		def (cuatrimestre, materia) = grupoService.abandonarGrupo(grupoId, alumnoDesertorId)

		[
				cuatrimestre: cuatrimestre,
				materia     : materia
		]
	}

	def agregar(Long grupoId, Long alumnoId) {
		[
				alumnos: grupoService.proponerAlumnos(grupoId, alumnoId),
				grupoId: grupoId
		]
	}

	def procesarAgregado(Long grupoId, Long alumnoId) {
		grupoService.agregarAlumno(grupoId, alumnoId)
		redirect(action: "show", controller: "grupo", id: grupoId)
	}

	def procesarArmado() {
		def materiaId = params.materiaId
		def cuatrimestreId = params.cuatrimestreId
		def nombre = params.nombre
		def ids = params.alumnoIds.split(',').collect { Long.valueOf(it) }

		grupoService.armar(nombre, materiaId, cuatrimestreId, ids)

		redirect(action: "listado", controller: "intencionDeFormarGrupo", params: [alumnoId: params["alumnoId"]])
	}

}
