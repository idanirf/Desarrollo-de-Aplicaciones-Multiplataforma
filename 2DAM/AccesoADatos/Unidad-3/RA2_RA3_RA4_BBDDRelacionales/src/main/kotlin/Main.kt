import Config.AppConfig
import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import controller.VehiculoController
import models.Vehiculo
import java.nio.file.Path
import java.nio.file.Paths

fun main(args: Array<String>) {
    AppConfig.initConfig()
    val controller = VehiculoController()

    val resoucesProperties = ClassLoader.getSystemResource("config.properties")
    val pathCsv = (Path.of(resoucesProperties.toURI()).parent).toString() + Paths.get(AppConfig.pathFilesCsv).toString()

    controller.initVehiculoFormCsv(pathCsv, true)

    println("Imprimir contenido de la BBDD" )
    println(controller.getAllVehiculos())

    println("Encontramos uno por id 1")
    var vehiculo : Vehiculo? = null
    controller.getVehiculoById(1).onSuccess { println(it) ; vehiculo=it  }.onFailure { println(it.message) }


    println("Encontramos uno por uuid ${vehiculo?.uuid}")

    vehiculo?.let{controller.getVehiculoByUuid(vehiculo!!.uuid).onSuccess { println(it) }.onFailure { println(it)}}


    println("Obtener vehiculos que no estan disponibles")
    controller.getVehiculoById(-1).onSuccess { println(it) ; vehiculo=it  }.onFailure { println(it.message) }

    println("Borrar vehiculo con id 1")
    vehiculo?.let { it1 -> controller.deleteVehiculo(it1.id).onSuccess { println(it) } }?.onFailure { println(it.message) }

    println("Imprimir contenido de la BBDD, no se ve el vehiculo borrado" )
    println( controller.getAllVehiculos())

    println("Buscamos por id 2 para modificar, cambiamos modelo")
    controller.getVehiculoById(2).onSuccess {
        println(it)
        it.modelo= "X5"
        controller.saveVehiculo(it!!).onSuccess {
            println("Base de datos actualizada correctamente")
            println(it) }.onFailure { println(it.message) }

    }.onFailure { println(it.message) }

    println("Obtener vehiculos y ver modificados" )
    println( controller.getAllVehiculos())

    println("Vaciar BBDD")
    controller.dropAllVehiculos()

    println("Borramos todos los vehiculos de la BBDD" )
    var listaVehiculos = controller.getAllVehiculos()
    println(listaVehiculos )

    controller.initVehiculoFormCsv(pathCsv,true)

    println("Recuperamos los vehículos" )
    listaVehiculos = controller.getAllVehiculos()
    println(listaVehiculos )
    }

