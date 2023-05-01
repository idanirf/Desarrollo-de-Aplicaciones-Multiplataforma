import kotlinx.coroutines.*
import models.Mecanico
import models.Vehiculo
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

suspend fun main(args: Array<String>) {
    println("Taller - Coroutines")
    println("====================")
    ejercicioAwait()
    ejercicioAsync()
}

suspend fun ejercicioAwait() {
    var time = measureTimeMillis {
        var escope = CoroutineScope(Dispatchers.Default)
        var trabajos = mutableListOf<Job>()
        var total = AtomicInteger(0)
        repeat(30) {
            var res = escope.launch {
                var listaVehiculos = ArrayList<Vehiculo>()
                repeat(30) {
                    listaVehiculos.add(Vehiculo())
                }
                var mecanico = Mecanico(listaVehiculos)
                mecanico.listVehiculos.forEach {
                    delay(it.tiempoReparacion.toLong())
                    total.addAndGet(it.precio)
                }
            }
            trabajos.add(res)
        }
        trabajos.forEach {
            it.join()
        }
    }
    println("Tiempo en ejecución: $time")
}

suspend fun ejercicioAsync() = coroutineScope {
    var time = measureTimeMillis {
        var escope = CoroutineScope(Dispatchers.Default)
        var resultados = mutableListOf<Deferred<Int>>()
        repeat(30) {
            var res = escope.async {
                var listaVehiculos = ArrayList<Vehiculo>()
                repeat(30) { listaVehiculos.add(Vehiculo()) }
                var mecanico = Mecanico(listaVehiculos)
                var total: Int = 0
                mecanico.listVehiculos.forEach {
                    delay(it.tiempoReparacion.toLong())
                    total += it.precio
                }
                return@async total

            }
            resultados.add(res)
        }
        var total = resultados.map { it.await() }

    }
    println("Tiempo en ejecución: $time")
}


