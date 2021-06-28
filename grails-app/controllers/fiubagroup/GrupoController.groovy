package fiubagroup

class GrupoController {

    static scaffold = Grupo

    def armar(Integer formularioId) {
        [
            alumnosPropuesto: [
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
            ],
            grupos: [
                new Grupo(nombre: "grupo1", materia: null, cuatrimestre: null, alumnos: []),
                new Grupo(nombre: "grupo2", materia: null, cuatrimestre: null, alumnos: [])
            ]
        ]
    }

}
