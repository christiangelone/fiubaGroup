package fiubagroup

class GrupoController {

	static scaffold = Grupo

	def grupoService

	def formar(Integer intencionDeFormarGrupoId) {

		def intencionesDeFormarGrupo = IntencionDeFormarGrupo.findById(intencionDeFormarGrupoId)

		def alumnos = []
		def cuatrimestre = null
		def materia = null
		def alumnoFormador = null
		if (intencionesDeFormarGrupo != null) {
			alumnos = grupoService.proponerAlumnos(intencionesDeFormarGrupo)
			cuatrimestre = intencionesDeFormarGrupo.cuatrimestre
			materia = intencionesDeFormarGrupo.materia
			alumnoFormador = intencionesDeFormarGrupo.alumno
		}

		[
				alumnoId              : alumnoFormador.id,
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


	def puntuar(Long grupoId, Long alumnoPuntuadorId) {
		def grupo = Grupo.findById(grupoId)

		def alumnos = grupoService.obtenerAlumnosDelGrupo(grupoId, alumnoPuntuadorId) ?: []

		def cuatrimestre = grupo?.cuatrimestre
		def materia = grupo?.materia

		[
				alumnoPuntuadorId: alumnoPuntuadorId,
				grupoId          : grupoId,
				alumnos          : alumnos,
				cuatrimestre     : cuatrimestre,
				materia          : materia
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
				alumnos: grupoService.proponerAlumnosFaltantes(grupoId, alumnoId),
				grupoId: grupoId
		]
	}

	def procesarFormado(Long grupoId, Long alumnoId) {
		grupoService.agregarAlumno(grupoId, alumnoId)
		redirect(action: "show", controller: "grupo", id: grupoId)
	}

	def procesarFormado() {
		def materiaId = params.materiaId
		def cuatrimestreId = params.cuatrimestreId
		def nombre = params.nombre
		def ids = params.alumnoIds.split(',').collect { Long.valueOf(it) }

		grupoService.formar(nombre, materiaId, cuatrimestreId, ids)

		redirect(action: "listado", controller: "intencionDeFormarGrupo", params: [alumnoId: params["alumnoId"]])
	}

}
