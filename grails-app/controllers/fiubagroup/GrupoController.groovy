package fiubagroup

class GrupoController {

    static scaffold = Grupo

    def armar(Integer formularioId) {

        //def formulario = FormularioDeCursada.findById(formularioId)

        def service = new GrupoService()

        def alumnos = [
            new Alumno(
                    nombre: "Pepe",
                    padron: 104678,
                    mail: "pepe@fi.uba.ar",
                    bandaHoraria: BandaHoraria.TARDE,
                    puntuacion: 3),
                new Alumno(
                    nombre: "Carlos",
                    padron: 103678,
                    mail: "carlos@fi.uba.ar",
                    bandaHoraria: BandaHoraria.TARDE,
                    puntuacion: 3)
        ]

        [
            alumnos: alumnos,
            alumnosIdsStr: alumnos.inject("", { ids, alumno -> ids == "" ? alumno.nombre : ids + ",${alumno.nombre}" }),
            alumnosNombres: alumnos.collect { alumno -> alumno.nombre },
            grupos: [
                new Grupo(nombre: "grupo1", materia: null, cuatrimestre: null, alumnos: []),
                new Grupo(nombre: "grupo2", materia: null, cuatrimestre: null, alumnos: [])
            ]
        ]
    }

}
