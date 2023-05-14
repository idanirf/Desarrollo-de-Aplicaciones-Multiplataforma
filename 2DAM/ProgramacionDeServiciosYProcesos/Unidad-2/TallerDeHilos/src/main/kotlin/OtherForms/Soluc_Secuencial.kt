package OtherForms

import java.util.concurrent.Callable
import java.util.concurrent.Executors


fun main(args: Array<String>) {
    println("Taller de forma Secuencial")

    val numMecanicos = 3
    val cochesPorMecanico = (4..8).random()

    val mecanicos = mutableMapOf<String, List<String>>()

    for (i in 1..numMecanicos) {
        mecanicos["Mecánico $i"] = (1..cochesPorMecanico).map { "Coche $it" }
    }

    var tiempoTotalReparacion = 0
    var recaudacionTotal = 0

    val executor = Executors.newFixedThreadPool(numMecanicos)

    val tareas = mutableListOf<Callable<Int>>()

    for ((mecanico, coches) in mecanicos) {
        for (coche in coches) {
            tareas.add(Callable {
                val tiempoReparacion = (100..300).random()
                val precio = (50..200).random()
                println("$mecanico está reparando el coche $coche")
                Thread.sleep(tiempoReparacion.toLong())
                tiempoTotalReparacion += tiempoReparacion
                precio
            })
        }
    }

    val resultado = executor.invokeAll(tareas)

    for (r in resultado) {
        recaudacionTotal += r.get()
    }

    println("El tiempo total:  $tiempoTotalReparacion ms")
    println("Recaudación total: $recaudacionTotal euros")

    executor.shutdown()
}
