package fiubagroup

class Grupo {

	//static hasOne = [cuatrimestre: Cuatrimestre, materia: Materia]
	//static hasMany = [alumnos: Alumno]
	String nombre
	Cuatrimestre cuatrimestre
	Materia materia
	Integer cantidadDesertores = 0
	static hasMany = [alumnos: Alumno]

	static constraints = {
	}

	def agregar(List<Alumno> alumnos) {
		alumnos.each { alumno ->
			agregar(alumno)
		}
	}

	def agregar(Alumno alumno) {
		chequearSiAlumnoTieneIntencionDeFormarGrupo(alumno)
		chequearSiAlumnoTieneIntencionDeFormarGrupoSinGrupo(alumno)
		addToAlumnos(alumno)
		if (cantidadDesertores > 0) {
			cantidadDesertores--
		}
	}

	def removerAlumno(Alumno alumno) {
		cantidadDesertores++
		removeFromAlumnos(alumno)
	}

	def tieneDesertores() {
		return cantidadDesertores > 0
	}

	def esVotables() {
		return cuatrimestre.estaFinalizado()
	}

	private def chequearSiAlumnoTieneIntencionDeFormarGrupo(alumno) {
		if (!alumno.tieneIntencionDeFormarGrupo(materia, cuatrimestre)) {
			throw new IllegalStateException(
					"el alumno ${alumno.nombre} no tiene intencionDeFormarGrupo " +
							"para la materia ${materia.codigo} " +
							"y cuatrimestre (año: ${cuatrimestre.anio}, ${cuatrimestre.numero})"
			)
		}
	}

	private def chequearSiAlumnoTieneIntencionDeFormarGrupoSinGrupo(alumno) {
		if (!alumno.tieneIntencionDeFormarGrupoSinGrupo(materia, cuatrimestre)) {
			throw new IllegalStateException(
					"el alumno ${alumno.nombre} ya tiene grupo " +
							"para la materia ${materia.codigo} " +
							"y cuatrimestre (año: ${cuatrimestre.anio}, ${cuatrimestre.numero})"
			)
		}
	}

	def todosPuntuaronA(Alumno alumno) {
		def grupoRef = this
		return alumnos.inject(true) { acumulado, alumnoDelGrupo ->
			acumulado && alumnoDelGrupo.tienePuntuacionDe(grupoRef, alumno)
		}
	}

	private def obtenerPuntuacionGrupalPara(Alumno alumno) {
		return alumnos.findAll { esteAlumno -> esteAlumno != alumno }.collect { alumnoDelGrupo ->
			alumnoDelGrupo.obtenerPuntosParaEste(this, alumno)
		}.flatten()
	}

	def esPenalizable(Alumno alumno){
		if(todosPuntuaronA(alumno)) {
			return obtenerPuntuacionGrupalPara(alumno).inject(true) { acumulado, punto ->
				acumulado && punto <= 2
			}
		}else{
			return false
		}
	}

	def esPremiable(Alumno alumno){
		if(todosPuntuaronA(alumno)) {
			return obtenerPuntuacionGrupalPara(alumno).inject(true) { acumulado, punto ->
				acumulado && punto > 3
			}
		}else{
			return false
		}
	}
}
