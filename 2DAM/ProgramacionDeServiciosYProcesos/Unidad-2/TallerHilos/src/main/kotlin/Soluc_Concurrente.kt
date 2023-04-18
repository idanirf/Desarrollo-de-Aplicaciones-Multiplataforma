import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

fun main(){
    val numMecanicos = 3
    val cochesPorMecanico = (4..8)

    val mecanicos = mutableMapOf<String, MutableList<Int>>()

    for (i in 1..numMecanicos) {
        val coches = (1..cochesPorMecanico.random()).map { it }
        mecanicos["Mecánico $i"] = coches.toMutableList()
    }
    val executor = Executors.newFixedThreadPool(numMecanicos)

    val reparaciones = mutableListOf<Future<Int>>()

    var tiempoTotalReparacion = 0
    var recaudacionTotal = 0

    for ((mecanico, coches) in mecanicos) {
        for (coche in coches) {
            val reparacion = executor.submit(Callable<Int> {
                val tiempoReparacion = (100..300).random()
                val precio = (50..200).random()
                println("$mecanico está reparando el coche $coche")
                Thread.sleep(tiempoReparacion.toLong())
                precio
            })
            reparaciones.add(reparacion)
        }
    }

    reparaciones.forEach {
        recaudacionTotal += it.get()
        tiempoTotalReparacion += (100..300).random()
    }

    println("El tiempo total empleado en la reparación de los coches es de $tiempoTotalReparacion ms")

    println("La recaudación total es de $recaudacionTotal €")

    executor.shutdown()
}