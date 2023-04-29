package rentrantLock

import Vehiculo
import java.lang.Thread.sleep
import java.util.concurrent.Callable
import kotlin.random.Random

class Mecanico(taller: Taller, descanso: Pair<Long, Long>, tiempoReparacion: Pair<Long, Long>) : Callable<Long> {
    var taller = taller
    var tiempoDescanso = descanso
    var tiempoReparacion = tiempoReparacion
    var enTrabajo = true
    var totalFacturado = 0L
    override fun call(): Long {
        while (enTrabajo) {
            sleep(Random.nextLong(tiempoDescanso.first, tiempoDescanso.second))
            var vehiculo: Vehiculo? = taller.sacarVehiculo()
            if (vehiculo == null) {
                enTrabajo = false
                } else {
                    sleep(Random.nextLong(tiempoReparacion.first, tiempoReparacion.second))
                    println("Un coche más arreglado que bueno soy y que culito tengo")
                    totalFacturado += vehiculo.precio
                }
            }
            println("Estoy trabajando: $enTrabajo")

        return totalFacturado
    }

}