package rentrantLock

import java.lang.Thread.sleep
import kotlin.random.Random

class Grua(parking: Parking, taller: Taller, tiempoGruas: Pair<Long, Long>):Runnable {
    val parking = parking
    val taller = taller
    var estadoTaller = true
    var tiempoGruas = tiempoGruas
    override fun run() {
        while (estadoTaller) {
            var vehiculo = parking.obtenerParking()
            sleep(Random.nextLong(tiempoGruas.first, tiempoGruas.second))
            println("Grua -> vehiculo dentro del taller")
            estadoTaller = taller.agregarVehiculo(vehiculo)
        }
        println("Grua -> me voy el taller esta cerrado")

    }
}