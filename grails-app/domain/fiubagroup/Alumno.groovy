package fiubagroup

import fiubagroup.exceptions.AlumnoNoPuntuado
import fiubagroup.exceptions.PuntuacionInvalida

class Alumno {
	// TODO - Ver telefono
	String nombre
	Integer padron
	String mail
	BandaHoraria bandaHoraria
	Integer puntuacionPromedio

	static hasMany = [intencionDeFormarGrupo: IntencionDeFormarGrupo, puntuaciones: Puntuacion]
	static constraints = {
		puntuacionPromedio defaultValue: 3
	}

	def private obtenerIntencionDeFormarGrupoPara(Materia materia, Cuatrimestre cuatrimestre) {
		def formularios = this.intencionDeFormarGrupo.findAll { f -> f.tieneCuatrimestreYMateria(materia, cuatrimestre) }
		return formularios.size() > 0 ? formularios[0] : null
	}

	def tieneIntencionDeFormarGrupo(Materia materia, Cuatrimestre cuatrimestre) {
		return obtenerIntencionDeFormarGrupoPara(materia, cuatrimestre) != null
	}

	// Todo renombrar a tieneIntencionDeFormarGrupoSinGrupoAsignado
	def tieneIntencionDeFormarGrupoSinGrupo(Materia materia, Cuatrimestre cuatrimestre) {
		return !obtenerIntencionDeFormarGrupoPara(materia, cuatrimestre)?.tieneGrupo() ?: false
	}

	def alumnosAfines(List<Alumno> alumnosEnLaMismaCursada, List<Alumno> alumnosEnGruposPrevios) {

		def alumnosConMasMenosUnoPuntuacion = alumnosEnLaMismaCursada.findAll {
			it.puntuacionPromedio >= puntuacionPromedio - 1 && it.puntuacionPromedio <= puntuacionPromedio + 1
		}

		def alumnosEnAlMenosDosGruposPrevios = alumnosEnGruposPrevios
				.groupBy { it.padron }
				.findAll { k, v -> v.size() >= 2 }
				.collect { k, v -> v[0] }
		def alumnosBloqueados = puntuaciones.findAll { it.puntos == 0 }.collect { it.alumnoPuntuado }
		def alumnosPropuestos = (alumnosConMasMenosUnoPuntuacion + alumnosEnAlMenosDosGruposPrevios).unique()
		return alumnosPropuestos.findAll { !alumnosBloqueados.contains(it) }
	}

	def puntuar(Puntuacion puntuacion) {
		if (puntuacion.puntos >= 0 && puntuacion.puntos <= 5) {
			def puntuacionInteger = (puntuacion.puntos + puntuacionPromedio) / 2
			setPuntuacionPromedio(puntuacionInteger.toInteger())
		} else {
			throw new PuntuacionInvalida()
		}
	}

	def puedePuntuar(Alumno alumnoPuntuado, Grupo grupo) {
		return puntuaciones.findAll { it.alumnoPuntuado == alumnoPuntuado && it.grupo == grupo }.isEmpty()
	}

	def tienePuntuacionDe(Grupo grupo, Alumno alumno) {
		if (alumno == this)
			return true
		return !obtenerPuntuacionesPara(grupo, alumno).isEmpty()
	}

	def obtenerPuntosParaEste(Grupo grupo, Alumno alumno) {
		def puntos = obtenerPuntuacionesPara(grupo, alumno).first()?.puntos

		if (puntos == null) {
			throw new AlumnoNoPuntuado(grupo, alumno)
		}
		return puntos

	}

	private def obtenerPuntuacionesPara(Grupo grupo, Alumno alumno) {
		return puntuaciones.findAll { it.grupo == grupo && it.alumnoPuntuado == alumno }
	}

	def penalizar() {
		if (puntuacionPromedio > 0) {
			setPuntuacionPromedio(puntuacionPromedio - 1)
		}
	}

	def premiar() {
		if (puntuacionPromedio < 5) {
			setPuntuacionPromedio(puntuacionPromedio + 1)
		}
	}

	def puntuo(Puntuacion puntuacion) {
		addToPuntuaciones(puntuacion)
	}
}


