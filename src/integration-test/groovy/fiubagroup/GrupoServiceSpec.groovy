package fiubagroup

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.*
import spock.lang.*

@Integration
@Rollback
class GrupoServiceSpec extends Specification {

    def grupoService

    def setup() {
    }

    def cleanup() {
    }

    void "proponer alumnos"() {
        given:
            def cuatrimestre = new Cuatrimestre(
                anio: 2021,
                numero: 1
            )
            cuatrimestre.save()

            def materia = new Materia(codigo: "AM2")
            materia.save(flush:true)

            def alumno = new Alumno(
                nombre: "Gero",
                padron: 102333,
                mail: "gero@fi.uba.ar",
                bandaHoraria: BandaHoraria.TARDE,
                puntuacion: 2)
            alumno.save(flush:true)

            def formulario = new IntencionDeCursada(
                alumno: alumno,
                materia: materia,
                cuatrimestre: cuatrimestre
            )
            formulario.save(flush:true)

            def alumnos = []
            4.times { i ->
                def a = new Alumno(
                    nombre: "Alumno ${i}",
                    padron: i,
                    mail: "alumno${i}@fi.uba.ar",
                    bandaHoraria: BandaHoraria.TARDE,
                    puntuacion: 3)
                a.save(flush:true)
                alumnos.add(a)
            }

            for(a in alumnos){
                def f = new IntencionDeCursada(
                    alumno: a,
                    materia: materia,
                    cuatrimestre: cuatrimestre
                )
                f.save(flush:true)
            }

        when:
            def alumnosPropuestos = grupoService.proponerAlumnos(formulario)

        then: "alumnos propuestos no vacio"
            alumnosPropuestos.isEmpty() == false
    }
}
