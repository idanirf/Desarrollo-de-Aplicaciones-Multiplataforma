package OtherForms

import kotlin.concurrent.thread

fun main() {
    println("Taller de forma Secuencial")

    val numMecanicos = 3
    val cochesPorMecanico = (4..8).random()

    val mecanicos = mutableMapOf<String, List<String>>()

    for (i in 1..numMecanicos) {
        mecanicos["Mecánico $i"] = (1..cochesPorMecanico).map { "Coche $it" }
    }

    var tiempoTotalReparacion = 0
    var recaudacionTotal = 0


    val hilos = mutableListOf<Thread>()

    for ((mecanico, coches) in mecanicos) {
        for (coche in coches) {
            val hilo = thread {
                val tiempoReparacion = (100..300).random()
                val precio = (50..200).random()
                println("$mecanico está reparando el coche $coche")
                Thread.sleep(tiempoReparacion.toLong())

            }
            hilos.add(hilo)
        }
    }

    for (hilo in hilos) {
        hilo.join()
    }

    println("El tiempo total:  $tiempoTotalReparacion ms")
    println("Recaudación total: $recaudacionTotal euros")
}
