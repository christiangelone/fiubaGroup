package fiubagroup

class Cuatrimestre {
	Integer anio
	Integer numero
	static constraints = {
	}

	def obtenerCuatrimestreActual() {
		return new Cuatrimestre(anio: 2021, numero: 2)
	}

	def estaFinalizado() {
		return anio <= obtenerCuatrimestreActual().anio && numero <= obtenerCuatrimestreActual().numero
	}
}
