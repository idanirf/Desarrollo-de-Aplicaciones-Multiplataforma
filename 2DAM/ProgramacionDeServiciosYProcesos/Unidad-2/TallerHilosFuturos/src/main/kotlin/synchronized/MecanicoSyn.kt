package synchronized

import Vehiculo
import java.lang.Thread.sleep
import java.util.concurrent.Callable
import kotlin.random.Random

class MecanicoSyn(taller: TallerSyn, descanso: Pair<Long, Long>, tiempoReparacion: Pair<Long, Long>): Callable<Long> {
    var taller = taller
    var timepoDescanso = descanso
    var tiempoReparacion = tiempoReparacion
    var estadoTrabajando = true
    var totalFacturado = 0L

    override fun call(): Long {
        while (estadoTrabajando) {
            println("Mecanico -> esta descansando")
            sleep(Random.nextLong(timepoDescanso.first, timepoDescanso.second))
            println("Mecanico -> quiero coger un vehiculo del taller, estoy libre")
            var vehiculo: Vehiculo? = taller.obtenerVehiculoTaller()
            println("Mecanico -> ya tengo un vehiculo")
            if (vehiculo == null) {
                estadoTrabajando = false
            }else {
                sleep(Random.nextLong(timepoDescanso.first, tiempoReparacion.second))
                println("Jefehhh otro vehiculo que he arreglado, que bueno soy y que sqlitotengo")
                totalFacturado += vehiculo.precio
            }
            println("Mecanico -> estado de trabajo ->$estadoTrabajando")
        }
        println("Mecanico -> me voy que ya he hecho mi media jornada 12 horas, !Hasta luego Maricarmen!")
        return totalFacturado
    }
}