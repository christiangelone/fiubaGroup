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
		def alumno = Alumno.findById(alumnoId)
		def gruposFiltrados = Grupo.findAll {
			it.alumnos.contains(alumno)
		}
		return [
				grupos  : gruposFiltrados,
				alumnoId: alumnoId
		]
	}


	def votar(Long grupoId, Long alumnoVotanteId) {
		def grupo = Grupo.findById(grupoId)

		def alumnos = grupoService.obtenerAlumnosDelGrupo(grupoId,alumnoVotanteId) ?: []

		def cuatrimestre = grupo?.cuatrimestre
		def materia = grupo?.materia

		[
				alumnoId    : alumnoVotanteId,
				alumnos     : alumnos,
				cuatrimestre: cuatrimestre,
				materia     : materia
		]
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
