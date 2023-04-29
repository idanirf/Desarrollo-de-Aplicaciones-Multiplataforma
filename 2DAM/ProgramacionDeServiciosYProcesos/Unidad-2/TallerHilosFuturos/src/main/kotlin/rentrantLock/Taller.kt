package rentrantLock

import Vehiculo
import java.util.concurrent.locks.ReentrantLock

class Taller(var plazas: Int = 5, vehiculosDia: Int = 60) {
    private var buffer = ArrayList<Vehiculo>()
    private var cerrojo = ReentrantLock()
    private val tiempoEsperaAgregar = cerrojo.newCondition()
    private val tiempoEsperaSacar = cerrojo.newCondition()
    private var vehiculosEnTaller = 0
    private var vehiculosReparacionPorDia = vehiculosDia
    private var estadoTaller = true

    fun agregarVehiculo(vehiculo: Vehiculo): Boolean {
        cerrojo.lock()
        try {
            while (buffer.size >= plazas) {
                println("Taller lleno voy a esperar")
                tiempoEsperaAgregar.await()
            }
            if (vehiculosEnTaller == vehiculosReparacionPorDia) {
                println("Agenda del taller completa para hoy")
                estadoTaller = false
            }else{
                buffer.add(vehiculo)
                vehiculosEnTaller++
                println("Agenda llena")
                tiempoEsperaSacar.signal()
            }
        }finally {
            println(buffer.toString())
            cerrojo.unlock()
        }
        return estadoTaller
    }

    fun sacarVehiculo(): Vehiculo? {
        cerrojo.lock()
        try {
            if (!estadoTaller && buffer.size <=0) {
                println("Hay hueco en la agenda")
                tiempoEsperaAgregar.signal()
                return null
            }
            while(buffer.size == 0) {
                println("Hay hueco en la agenda, voy a esperar")
                tiempoEsperaSacar.await()
            }
            var vehiculo = buffer.removeAt(0)
            println("Hay hueco en la agenda")
            return vehiculo

        }finally {
            cerrojo.unlock()
        }
    }
}