import Controller.HibernateManager
import models.Persona
import models.Vehiculo
import repository.vehiculo.VehiculoRepositoryImpl
import java.util.*

fun main(args: Array<String>) {
    println("Hello Hibernate JPA!")
    initDatabase()
    var vehiculosRepository = VehiculoRepositoryImpl()

    var persona = Persona("Dani", "dani.dani@gmail.com", "14-01-2022")

    println("Creamos y añadimos Vehiculo ")
    var vehiculo = Vehiculo(UUID.randomUUID().toString(), "BMW", "X1", motor ="Gasolina", matricula = "1234PPP", personas = persona )
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