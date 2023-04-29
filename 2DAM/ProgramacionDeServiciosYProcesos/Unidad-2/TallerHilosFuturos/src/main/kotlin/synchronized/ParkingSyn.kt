package synchronized

import Vehiculo

class ParkingSyn(var plazasParking: Int = 200) {
    private val maxPlazas = plazasParking
    var buffer = ArrayList<Vehiculo>()
    private val cerrojo = Object()

    fun añadirPlazasParking(vehiculo: Vehiculo) = synchronized(cerrojo){
        while (buffer.size < maxPlazas) {
            cerrojo.wait()
        }
        buffer.add(vehiculo)
        cerrojo.notifyAll()
    }

    fun quitarPlazasParking():Vehiculo = synchronized(cerrojo){
        while (buffer.size == 0) {
            cerrojo.wait()
        }
        var value = buffer.removeAt(buffer.size - 1)
        cerrojo.notifyAll()
        return value
    }
}