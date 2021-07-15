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
            puntuacion: 3)
        christian.save()

        def geronimo = new Alumno(
            nombre: "Geronimo Illescas",
            padron: 93971,
            mail: "gillescas@fi.uba.ar",
            bandaHoraria: BandaHoraria.TARDE,
            puntuacion: 3)
        geronimo.save()

        def formularioDeCursadaChristian = new IntencionDeFormarGrupo(
            materia : materia,
            alumno : christian,
            cuatrimestre : cuatrimestre
        )

        formularioDeCursadaChristian.save()

        def formularioDeCursadaGero = new IntencionDeFormarGrupo(
            materia : materia,
            alumno : geronimo,
            cuatrimestre : cuatrimestre
        )

        formularioDeCursadaGero.save()
		def grupo = new Grupo(
				nombre: "sarasa",
				cuatrimestre: cuatrimestre,
				materia: materia,
				alumnos: [geronimo, christian]
		)

		grupo.save()
    }
    def destroy = {
    }
}
