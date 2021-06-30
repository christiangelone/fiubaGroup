package fiubagroup

class Alumno {
    // TODO - Ver telefono
    String nombre
    Integer    padron
    String mail
    BandaHoraria bandaHoraria
    Integer    puntuacion

    //static hasMany: [formularioDeCursada: FormularioDeCursada]
    static constraints = {
    }

    def alumnosAfines(List<Alumno> alumnosEnLaMismaCursada, List<Alumno> alumnosEnGruposPrevios) {

        return  [
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
    }

    def puntuar(Integer nuevaPuntuacion) {
        if(nuevaPuntuacion >=1 && nuevaPuntuacion <= 5) {
            puntuacion = (nuevaPuntuacion + puntuacion) / 2
        }

        if(nuevaPuntuacion == 0) {
            // Bloquear alumno
        }
    }
}
