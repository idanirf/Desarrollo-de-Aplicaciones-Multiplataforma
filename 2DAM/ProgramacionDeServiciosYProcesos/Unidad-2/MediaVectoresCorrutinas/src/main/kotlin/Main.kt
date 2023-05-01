import kotlinx.coroutines.*
import java.util.concurrent.LinkedBlockingDeque
import kotlin.random.Random
import kotlin.system.measureTimeMillis

suspend fun main(args: Array<String>) {
    println("Media de vectores - Corrutinas")
    var vector = randomList(106)

    var vectorLaunch = ArrayList<Int>()
    vector.forEach {vectorLaunch.add(it)}
    ejercicioLaunch(vectorLaunch)

    var vectorAsync = ArrayList<Int>()
    vector.forEach {vectorAsync.add(it)}
    ejercicioAsync(vectorAsync)
}

suspend fun ejercicioLaunch(vector: ArrayList<Int>) {
    var time = measureTimeMillis {
        var vectorMedia = LinkedBlockingDeque<Double>()
        var listaTrabajos = ArrayList<Job>()
        var escope = CoroutineScope(Dispatchers.IO)
        vector.windowed(10).forEach{
            var x = escope.launch {
               vectorMedia.add(it.average())
            }
            listaTrabajos.add(x)
        }
        var res = listaTrabajos.map { it.join() }
        println("Lista total: " + vectorMedia.sum())
    }
    println("Con lanch el tiempo tardado es : $time")
}

suspend fun ejercicioAsync(vector: ArrayList<Int>) = coroutineScope {
    var time = measureTimeMillis {
        var escope = CoroutineScope(Dispatchers.Default)
        var res: List<Deferred<Double>> = vector.windowed(10).map {
            escope.async {
                it.average()
            }
        }
        println("Lista Total: " + res.sumOf { it.await() })
    }
    println("Con Async el tiempo tardado es : $time")
}



fun randomList(cantidad : Int): ArrayList<Int> {
    var vector = ArrayList<Int> ()
    repeat(cantidad){vector.add(Random.nextInt(0,101))}
    return  vector

}