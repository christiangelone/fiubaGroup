package fiubagroup

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

@Transactional
class GrupoServiceSpec extends Specification implements ServiceUnitTest<GrupoService>{


    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
    given:
        def cuatrimestre = new Cuatrimestre(
            anio: 2021,
            numero: 1
        )
        cuatrimestre.save()

        def materia = new Materia(codigo: "AM2")
        materia.save()

        def alumno = new Alumno(
            nombre: "Gero",
            padron: 102333,
            mail: "gero@fi.uba.ar",
            bandaHoraria: BandaHoraria.TARDE,
            puntuacion: 2)
        alumno.save()

        def formAlumno = new FormularioDeCursada(
            alumno: alumno,
            materia: materia,
            cuatrimestre: cuatrimestre
        )
        formAlumno.save()

        def alumnos = []
        1.upto(4) { i -> 
            def a = new Alumno(
            nombre: "Alumno ${i}",
            padron: $i,
            mail: "alumno${i}@fi.uba.ar",
            bandaHoraria: BandaHoraria.TARDE,
            puntuacion: 3)
            a.save()
            alumnos.add(a)
        }
        
        for(a in alumnos){
            def form = new FormularioDeCursada(
                alumno: a,
                materia: materia,
                cuatrimestre
            )
            form.save()
        }


        expect:"fix me"
            true == service.proponerAlumnos(formAlumno).isEmpty()
    }
}
