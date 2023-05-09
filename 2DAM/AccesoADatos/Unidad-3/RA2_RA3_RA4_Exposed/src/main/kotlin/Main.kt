import db.DataBaseManager
import models.Persona
import models.Vehiculo
import repository.PersonaRepositoryImpl
import repository.VehiculoRepositoryImpl

fun main(args: Array<String>) {
    println("Hello Exposed!")
    DataBaseManager.init()
    var personaRepository = PersonaRepositoryImpl()
    var vehiculoRepository = VehiculoRepositoryImpl()

    var dani: Persona? = Persona(nombre = "Dani", fechaCarntet = "14-01-2022")
    println(dani)

    dani = dani?.let {
        personaRepository.create(it)
    }

    println(dani)

    // Buscamos por id
    if (dani!= null) {
        println(personaRepository.findById(dani.id))
    }

    // Buscamos todos
    println(personaRepository.findAll())

    // Modificamos personas
    if (dani!= null) {
        dani.nombre = "Jose Luis"
        println("Modificamos persona: $dani")
        personaRepository.update(dani)
    }

    // Buscamos a la persona por id para ver la modificación
    if (dani!= null) {
        println(personaRepository.findById(dani.id))
    }

    // Buscamos todos
    println(personaRepository.findAll())

    // Borramos
    if (dani!= null) {
        println(personaRepository.deleteById(dani.id))
    }

    // FindAll
    println(personaRepository.findAll())

    // Vehiculos
    var vehiculo: Vehiculo? = Vehiculo(marca = "BMW", modelo = "X1", matricula = "1234HHH")
    vehiculo = vehiculo?.let {vehiculoRepository.create(vehiculo!!)}

    println(vehiculo)

    if (vehiculo!= null) {
        println(vehiculoRepository.findById(vehiculo.uuid))
    }

    println(vehiculoRepository.findAll())

    // Borramos vehiculo
    if (vehiculo!= null) {
        vehiculoRepository.deleteById(vehiculo.uuid)
    }

    // Mostrar todos
    println(vehiculoRepository.findAll())

    var danito: Persona? = Persona(nombre = "Dan", fechaCarntet = "14-01-2023")
    if (danito!= null) {
        danito = danito?.let {personaRepository.create(danito!!)}
    }

    println(personaRepository.findAll())

    var vehi: Vehiculo? = Vehiculo(marca = "BMW", modelo="X2", persona = danito?.id)
    println(vehi)

    if (vehi!= null) {
        println(vehiculoRepository.create(vehi))
    }

    println(vehiculoRepository.findAll())


}