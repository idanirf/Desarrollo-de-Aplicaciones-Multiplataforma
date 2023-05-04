import Controller.HibernateManager
import models.Persona
import models.Vehiculo
import repository.persona.PersonaRepositoryImpl
import repository.vehiculo.VehiculoRepositoryImpl
import java.util.*

fun main(args: Array<String>) {
initDatabase()
    var personaRepository = PersonaRepositoryImpl()
    var vehiculosRepository = VehiculoRepositoryImpl()

    println("Creamos y añadimos Personas ")
    var pers = Persona(nombre = "Danii" )
    println(pers)

    pers = personaRepository.create(pers)
    println(pers)
    println("Buscamos persona por id")
    println(personaRepository.getById(1))

    println("Leemos todas las personas")
    println(personaRepository.getAll())

    println("Modificamos persona")
    pers.nombre = "Asara"
    personaRepository.update(pers)

    println("Leemos todas las personas")
    println(personaRepository.getAll())

    println("Borramos persona")
    personaRepository.delete(pers)

    println("Leemos todas las personas")
    println(personaRepository.getAll())

    println("Creamos y añadimos Vehiculo ")
    var vehiculo = Vehiculo(UUID.randomUUID().toString(), "BMW", "X1", motor ="Gasolina", matricula = "1234PPP")
    println(vehiculo)
    var vehiculo1 = Vehiculo(UUID.randomUUID().toString(), "Audi  pequeño", "A1", motor ="Diesel", matricula = "1222PPP")
    println(vehiculo1)

    vehiculo = vehiculosRepository.create(vehiculo)
    vehiculo1 = vehiculosRepository.create(vehiculo1)

    println(vehiculo)
    println(vehiculo1)

    println("Leemos todas los vehiculo")
    println(vehiculosRepository.getAll())

    println("Modificamos vehiculo")
    vehiculo1.modelo = "A6"
    vehiculosRepository.update(vehiculo1)

    println("Leemos todos los vehiculo")
    println(vehiculosRepository.getAll())

    println("Borramos vehiculo")
    vehiculosRepository.delete(vehiculo)

    println("Leemos todas las vehiculo")
    println(vehiculosRepository.getAll())
}
fun initDatabase() {
    HibernateManager.open()
    HibernateManager.close()
}