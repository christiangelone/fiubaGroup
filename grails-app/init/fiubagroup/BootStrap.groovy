package fiubagroup

class BootStrap {

    def init = { servletContext ->
        def materia = new Materia(codigo : "7174")
        materia.save()

        def cuatrimestre = new Cuatrimestre(numero : 1, anio : 2021)
        cuatrimestre.save()

        def christian = new Alumno(
            nombre: "Christian Angelone",
            padron: 93971,
            mail: "cangelone@fi.uba.ar",
            bandaHoraria: BandaHoraria.TARDE,
				puntuacionPromedio: 3)
        christian.save()

        def geronimo = new Alumno(
            nombre: "Geronimo Illescas",
            padron: 93972,
            mail: "gillescas@fi.uba.ar",
            bandaHoraria: BandaHoraria.TARDE,
				puntuacionPromedio: 3)
        geronimo.save()

		def ignacio = new Alumno(
				nombre: "Colombo Juan Ignacio",
				padron: 9393,
				mail: "Colombo@fi.uba.ar",
				bandaHoraria: BandaHoraria.TARDE,
				puntuacionPromedio: 3)
		ignacio.save()

		[christian, geronimo, ignacio].forEach{
			new IntencionDeFormarGrupo(
					materia : materia,
					alumno : it,
					cuatrimestre : cuatrimestre
			).save()
		}
    }
    def destroy = {
    }
}
