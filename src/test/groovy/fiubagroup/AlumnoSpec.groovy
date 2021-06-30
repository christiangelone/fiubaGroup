package fiubagroup

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class AlumnoSpec extends Specification implements DomainUnitTest<Alumno> {

    def setup() {
    }

    def cleanup() {
    }

    void "Proponer alumnos con puntaje entre +- puntaje de alumno"() {
        given:
            def alumno = new Alumno(
                                nombre: "Pepe",
                                padron: 104678,
                                mail: "pepe@fi.uba.ar",
                                bandaHoraria: BandaHoraria.TARDE,
                                puntuacion: 3)
            def pepe1 = new Alumno(
                                nombre: "Pepe1",
                                padron: 104678,
                                mail: "pepe@fi.uba.ar",
                                bandaHoraria: BandaHoraria.TARDE,
                                puntuacion: 2)
            def pepe2 = new Alumno(
                                nombre: "Pepe2",
                                padron: 104678,
                                mail: "pepe@fi.uba.ar",
                                bandaHoraria: BandaHoraria.TARDE,
                                puntuacion: 3)
            def pepe3 = new Alumno(
                                nombre: "Pepe3",
                                padron: 104678,
                                mail: "pepe@fi.uba.ar",
                                bandaHoraria: BandaHoraria.TARDE,
                                puntuacion: 5)
            def alumnosEnLaMismaCursada = [pepe1, pepe2, pepe3]
            def alumnosEnGruposPrevios = []

        when: 
            def alumnosAfines = alumno.alumnosAfines(alumnosEnLaMismaCursada, alumnosEnGruposPrevios)

        then:"solo pepe1 pepe2 deben estar en la propuesta"
            alumnosAfines.size() == 2
        and:
            alumnosAfines.contains(pepe1) == true
        and:    
            alumnosAfines.contains(pepe2) == true
    }

     void "No proponer alumnos con puntaje que no este entre +- puntaje de alumno"() {
        given:
            def alumno = new Alumno(
                                nombre: "Pepe",
                                padron: 104678,
                                mail: "pepe@fi.uba.ar",
                                bandaHoraria: BandaHoraria.TARDE,
                                puntuacion: 3)

            def pepe1 = new Alumno(
                                nombre: "Pepe1",
                                padron: 104678,
                                mail: "pepe@fi.uba.ar",
                                bandaHoraria: BandaHoraria.TARDE,
                                puntuacion: 1)
            def pepe2 = new Alumno(
                                nombre: "Pepe2",
                                padron: 104678,
                                mail: "pepe@fi.uba.ar",
                                bandaHoraria: BandaHoraria.TARDE,
                                puntuacion: 5)
            def alumnosEnLaMismaCursada = [pepe1, pepe2]
            def alumnosEnGruposPrevios = []
        when: 
            def alumnosAfines = alumno.alumnosAfines(alumnosEnLaMismaCursada, alumnosEnGruposPrevios)
        then:"los alumnos propuestos debe ser vacio"
            alumnosAfines.size() == 0
    }

    void "Proponer alumnos con al menos dos cursadas en comun previas"() {
        given:
            def alumno = new Alumno(
                                nombre: "Pepe",
                                padron: 104678,
                                mail: "pepe@fi.uba.ar",
                                bandaHoraria: BandaHoraria.TARDE,
                                puntuacion: 3)

            def pepe1 = new Alumno(
                                nombre: "Pepe1",
                                padron: 104673,
                                mail: "pepe@fi.uba.ar",
                                bandaHoraria: BandaHoraria.TARDE,
                                puntuacion: 1)
            def pepe2 = new Alumno(
                                nombre: "Pepe2",
                                padron: 104675,
                                mail: "pepe@fi.uba.ar",
                                bandaHoraria: BandaHoraria.TARDE,
                                puntuacion: 5)
            def alumnosEnLaMismaCursada = []
            def alumnosEnGruposPrevios = [pepe1, pepe1, pepe2]
        when: 
            def alumnosAfines = alumno.alumnosAfines(alumnosEnLaMismaCursada, alumnosEnGruposPrevios)
        then:"solo pepe1 pepe2 deben estar en la propuesta"
            alumnosAfines.size() == 1
        and:
            alumnosAfines.contains(pepe1) == true
        and:    
            alumnosAfines.contains(pepe2) == false
    }

      void "No proponer alumnos sin al menos dos cursadas en comun previas"() {
        given:
            def alumno = new Alumno(
                                nombre: "Pepe",
                                padron: 104678,
                                mail: "pepe@fi.uba.ar",
                                bandaHoraria: BandaHoraria.TARDE,
                                puntuacion: 3)

            def pepe1 = new Alumno(
                                nombre: "Pepe1",
                                padron: 104673,
                                mail: "pepe@fi.uba.ar",
                                bandaHoraria: BandaHoraria.TARDE,
                                puntuacion: 1)
            def pepe2 = new Alumno(
                                nombre: "Pepe2",
                                padron: 104675,
                                mail: "pepe@fi.uba.ar",
                                bandaHoraria: BandaHoraria.TARDE,
                                puntuacion: 5)
            def alumnosEnLaMismaCursada = []
            def alumnosEnGruposPrevios = [pepe1, pepe2]
        when: 
            def alumnosAfines = alumno.alumnosAfines(alumnosEnLaMismaCursada, alumnosEnGruposPrevios)
        then:"solo pepe1 pepe2 deben estar en la propuesta"
            alumnosAfines.size() == 0
        and:
            alumnosAfines.contains(pepe1) == false
        and:    
            alumnosAfines.contains(pepe2) == false
    }
}
