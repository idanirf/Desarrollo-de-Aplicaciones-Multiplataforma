import Config.AppConfig
import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import controller.VehiculoController
import models.Vehiculo
import java.nio.file.Paths

fun main(args: Array<String>) {
    AppConfig.initConfig()
    val controlador = VehiculoController()

    val resource = ClassLoader.getSystemResource("config.properties")
    var pathCsv =  (Paths.get(resource.toURI()).parent).toString() + Paths.get(AppConfig.pathFilesCsv).toString()

    controlador.getVehiculosFromCSV(pathCsv,true)

    println("Imprimir base de datos de vehiculos" )
    println(controlador.getAllVehiculos())

    println("Encontrar vehiculo por id 1")
    var vehiculo : Vehiculo? = null
    controlador.getVehiculoById(1).onSuccess { println(it) ; vehiculo=it  }.onFailure { println(it.message) }

    println("Encontrar vehiculo por uuid ${vehiculo?.uuid}")

    vehiculo?.let{controlador.getVehiculoByUuid(vehiculo!!.uuid).onSuccess { println(it) }.onFailure { println(it)}}


    println("Buscar por id que no existe")
    controlador.getVehiculoById(-1).onSuccess { println(it) ; vehiculo=it  }.onFailure { println(it.message) }

    println("Borrar por id 1")
    vehiculo?.let { it1 -> controlador.deleteVehiculo(it1.id).onSuccess { println(it) } }?.onFailure { println(it.message) }

    println("Imprimir contenido BBDD y ver que no esta el borrado" )
    println( controlador.getAllVehiculos())

    println("Buscar por id 2 para modificar")
    controlador.getVehiculoById(2).onSuccess {
        println(it)
        it.modelo= "idanirf"
        it.marca = "idanirf"
        controlador.saveVehiculo(it!!).onSuccess {
            println("La base de datos ha sido actualizada")
            println(it) }.onFailure { println(it.message) }

    }.onFailure { println(it.message) }

    println("Imprimir vehiculos vemos los cambios" )
    println( controlador.getAllVehiculos())

    println("Borramos todos los vehiculos")
    controlador.dropAllVehiculos()

    println("Imprimir contenido de la BBDD" )
    var listaVehiculos = controlador.getAllVehiculos()
    println(listaVehiculos)

    controlador.getVehiculosFromCSV(pathCsv,true)

    println("Obtenemos vehiculos y los almacenamos en la BBDD" )
    listaVehiculos = controlador.getAllVehiculos()
    println(listaVehiculos )

    System.exit(0)
}

