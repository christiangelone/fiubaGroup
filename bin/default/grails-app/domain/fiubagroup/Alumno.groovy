package fiubagroup

class Alumno {
    // TODO - Ver telefono
    String nombre
    Integer    padron
    String mail
    BandaHoraria bandaHoraria
    Integer    puntuacion

    static hasMany = [intencionDeFormarGrupo: IntencionDeFormarGrupo]
    static constraints = {
        puntuacion defaultValue: 3
    }

    def private obtenerIntencionDeFormarGrupoPara(Materia materia, Cuatrimestre cuatrimestre) {
        def formularios = this.intencionDeFormarGrupo.findAll{ f -> f.tieneCuatrimestreYMateria(materia, cuatrimestre) }
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
            it.puntuacion >= puntuacion - 1 && it.puntuacion <= puntuacion + 1
        }

        def alumnosEnAlMenosDosGruposPrevios = alumnosEnGruposPrevios
                                            .groupBy { it.padron }
                                            .findAll{ k, v -> v.size() >= 2}
                                            .collect { k, v -> v[0] }

        def alumnosPropuestos = (alumnosConMasMenosUnoPuntuacion + alumnosEnAlMenosDosGruposPrevios).unique()
        return alumnosPropuestos
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
