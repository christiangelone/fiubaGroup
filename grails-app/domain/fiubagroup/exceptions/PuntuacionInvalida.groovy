package fiubagroup.exceptions

class PuntuacionInvalida extends IllegalStateException {
	PuntuacionInvalida() {
		super("los puntos deben ser entre 0 y 5")
	}
}
