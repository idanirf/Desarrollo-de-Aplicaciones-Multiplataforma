import controller.ControllerVehiculos
import db.DBManager
import models.Persona
import models.Vehiculo
import repository.persona.PersonasRepository
import repository.vehiculo.VehiculoRepository

fun main(args: Array<String>) {
    DBManager.initCollection("personas")
    var pers = PersonasRepository()
    CRUDPersonas(pers)

    DBManager.viewDBases()
    DBManager.viewCollections()
    DBManager.initCollection("vehiculos")
    var vehiculo = VehiculoRepository()
    CRUDVehiculos(vehiculo)

    DBManager.viewDBases()
    DBManager.viewCollections()

    relationOneToMany(pers,vehiculo)
}

fun CRUDPersonas(personasRepository: PersonasRepository){
    personasRepository.deleteAll()

    DBManager.restartdb()
    println(personasRepository.findAll())

    var perOne = Persona(nombre = "Dani", email = "dani@gmail.com")
    personasRepository.create(perOne)

    println(personasRepository.findAll())

    var searchPers = personasRepository.findById(perOne.uuid)
    println(searchPers)

    personasRepository.delete(perOne.uuid)

    println(personasRepository.findAll())
}

fun CRUDVehiculos(vehiculosRepository: VehiculoRepository){
    println(vehiculosRepository.findAll())

    var vehiculoOne = Vehiculo(marca = "BMW", modelo = "X1", matricula = "1234KKK")
    vehiculosRepository.create(vehiculoOne)

    println(vehiculosRepository.findAll())

    var searchVehiculo = vehiculosRepository.findById(vehiculoOne.uuid)
    println(searchVehiculo)

    vehiculosRepository.delete(vehiculoOne.uuid)

    DBManager.viewDBases()
    DBManager.viewCollections()
}

fun relationOneToMany(personas: PersonasRepository, vehiculo: VehiculoRepository){
    val controller = ControllerVehiculos(personas, vehiculo)
    var vehiculo = Vehiculo(marca = "BMW", modelo = "X1", matricula = "1234KKK")
    var persOne = Persona(nombre = "Dani", email = "dani@gmail.es")

    controller.createPersona(persOne)
    controller.createVehiculo(vehiculo)

    println(controller.findAllPersonas())
    println(controller.findAllVehiculos())

    vehiculo.uuidPersona = persOne.uuid
    controller.updateVehiculo(vehiculo)

    println(controller.findAllPersonas())
    println(controller.findAllVehiculos())

    controller.deletePersona(persOne.uuid)
    println(controller.findAllPersonas())
    println(controller.findAllVehiculos())

}