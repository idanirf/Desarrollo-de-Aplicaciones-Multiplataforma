package synchronized

import java.lang.Thread.sleep
import kotlin.random.Random

class GruaSyn(parking: ParkingSyn, taller: TallerSyn, tiempoGrua: Pair<Long, Long>): Runnable {
    val parking = parking
    val taller = taller
    var estadoTaller = true
    var tiempoGrua = tiempoGrua

    override fun run() {
        while(estadoTaller){
            var vehiculo = parking.quitarPlazasParking()
            println("Grua -> moviendo vehiculo del parking")
            sleep(Random.nextLong(tiempoGrua.first,tiempoGrua.second))
            println("Grua -> el vehiculo está en la puerta del parking")
            estadoTaller = taller.añadirVehiculosEnTaller(vehiculo)
            println("Grua -> vehiculo dentro del taller")
        }
        println("Grua -> se acabó mi turno el taller esta cerrado")
    }

}