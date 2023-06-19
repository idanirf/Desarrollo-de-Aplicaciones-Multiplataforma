import controller.SystemController
import models.Alumno
import models.Asignatura
import repositories.alumno.AlumnoRepository
import repositories.asignatura.AsignaturaRepository
import java.util.*

fun main(args: Array<String>) {
    val alumnosRepository: AlumnoRepository = AlumnoRepository()
    val asignaturaRepository: AsignaturaRepository = AsignaturaRepository()

    var controller = SystemController(
        alumnosRepository,asignaturaRepository
    )
    println("Gestionando IES")
    val asignatura = Asignatura(
        id = UUID.randomUUID().toString(),
        codModulo = "0490",
        nombreModulo = "Programación de servicios y procesos",
        horasSemanales = 4
    )

    val alumno = Alumno(
        id = UUID.randomUUID().toString(),
        nombreCompleto = "Rodriguez Fernandez, Daniel",
        asignatura = asignatura
    )

    controller.save(asignatura)
    println("BUSCAMOS TODAS LAS ASIGNATURAS")
    println(controller.findAllAsignaturas())

    controller.save(alumno)
    println("BUSCAMOS TODOS LOS ALUMNOS")
    println(controller.findAllAlumnos())

    println("BUSCAMOS UN ALUMNOS POR ID")
    println(controller.findByIdAlumno(alumno.id))

    println(controller.delete(alumno))
    println(controller.findAllAlumnos())

}