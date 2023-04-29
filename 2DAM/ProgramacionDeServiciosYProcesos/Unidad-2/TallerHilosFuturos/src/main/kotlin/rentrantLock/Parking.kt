package rentrantLock

import Vehiculo
import java.util.concurrent.locks.ReentrantLock

class Parking(var plazas:Int=200) {
    private var buffer = ArrayList<Vehiculo>()
    private val cerrojo = ReentrantLock()
    private val tiempoEsperaInput = cerrojo.newCondition()
    private val tiempoEsperaOutput = cerrojo.newCondition()

    fun introducirParking(vehiculo: Vehiculo) {
        cerrojo.lock()
        try {
            while(buffer.size >= plazas){
                tiempoEsperaInput.await()
            }
            buffer.add(vehiculo)
            tiempoEsperaOutput.signal()
        } finally {
            cerrojo.unlock()
        }
    }

    fun obtenerParking(): Vehiculo {
        cerrojo.lock()
        try {
            while(buffer.size == 0){
                tiempoEsperaOutput.await()
            }
            var value = buffer.removeAt(buffer.size - 1)
            tiempoEsperaInput.signal()
            return value
        }finally {
            cerrojo.unlock()
        }
    }
}