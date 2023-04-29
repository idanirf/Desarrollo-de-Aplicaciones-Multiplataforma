package synchronized

import Vehiculo

class TallerSyn(var plazasTaller: Int = 5, vehiculosAlDia: Int = 60) {
    private var buffer = ArrayList<Vehiculo>()
    private var vehiculosEnTaller = 0
    private var vehiculosRepararPorDia = vehiculosAlDia
    private var estadoTaller = true
    private val cerrojo = Object()

    fun añadirVehiculosEnTaller(vehiculo: Vehiculo): Boolean = synchronized(cerrojo) {
        println("Añadir vehiculos en taller")
        while (buffer.size >= plazasTaller) {
            cerrojo.wait()
        }
        println("Hay plazas libres")
        if (vehiculosEnTaller == vehiculosRepararPorDia){
            println("Agenda del taller completa")
            estadoTaller = false
        } else {
            buffer.add(vehiculo)
            vehiculosEnTaller++
        }
        cerrojo.notifyAll()
        return estadoTaller
    }

    @Synchronized
    fun obtenerVehiculoTaller(): Vehiculo? = synchronized(cerrojo){
        if (!estadoTaller && buffer.size <=0) {
            println("Taller vacio, estamos cerrados")
            cerrojo.notifyAll()
            return null
        }
        while(buffer.size == 0){
            cerrojo.wait()
            if(!estadoTaller && buffer.size <=0) {
                println("Taller vacio, estamos cerrados")
                cerrojo.notifyAll()
                return null
            }
        }
        var vehiculo = buffer.removeAt(0)
        cerrojo.notifyAll()
        return vehiculo
    }
}