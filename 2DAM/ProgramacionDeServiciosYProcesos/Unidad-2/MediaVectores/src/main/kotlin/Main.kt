import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.atomic.AtomicInteger
import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    println("Hello Hilos Media Vectores")
    //Necesitamos fabricar 106 números para nuestro vector
    var vectorNumeros: ArrayList<Int> = fabricaNumeros(106)
    println("Listado de numeros:" + vectorNumeros)

    // Creamos una lista de números específicos para los hilos
    var vectorHilos = ArrayList<Int>()
    vectorNumeros.forEach { vectorHilos.add(it) }
    println("Listado de hilos:" + vectorHilos)
    ejercicioHilos(vectorHilos)

    // Creamos una lista de números específicos para los futuros
    var vectorFuturos = ArrayList<Int>()
    vectorNumeros.forEach { vectorFuturos.add(it) }
    println("Listado de futuros:" + vectorFuturos)
    ejercicioFuturos(vectorFuturos)
}


private fun ejercicioFuturos(vector: ArrayList<Int>) {
    println("\n \n")
    var tiempoFutures = measureTimeMillis {
        futuros(vector)
    }
    println("Tiempo Futuros : " + tiempoFutures)
}

private fun ejercicioHilos(vector: ArrayList<Int>) {
    println("\n \n")
    var tiempoHilos = measureTimeMillis {
        hilos(vector)
    }
    println("Tiempo Hilos: " + tiempoHilos)
}


//Ejercicio realizado con futuros
fun futuros(vector: ArrayList<Int>) {
    // Número de hilos que tiene nuestro poll
    var pollFuturos = Executors.newFixedThreadPool(7)
    var vectorFuturos = ArrayList<Future<Double>>()
    while(vector.isNotEmpty()){
        var vectorNumerosSecundaria = ArrayList<Int>()
        while(vectorNumerosSecundaria.size <10 && vector.isNotEmpty()){
            vectorNumerosSecundaria.add(vector.removeAt(0))
        }
        vectorFuturos.add(pollFuturos.submit(
            Callable<Double> {
                var media = vectorNumerosSecundaria.average()
                println(media)
                return@Callable media
            }
        ))
    }
    vectorFuturos.forEach { it.get() }
    var vectorMedia = vectorFuturos.map { it.get() }

    println()
    println("Imprimiendo listado de medias: $vectorMedia")
    println("El toral de medias: " + vectorMedia.sum())
}

fun hilos(vector: ArrayList<Int>) {
    // Creamos variables para sacar las medias, hilos que se encuentran en un proceso y el poll de hilos
    var mediaHilo = LinkedBlockingDeque<Double>()
    var hilosEjecucion = AtomicInteger(0)
    var pollHilos = Executors.newFixedThreadPool(7)
    while(vector.isNotEmpty()){
        var vectorHilosSecundaria = ArrayList<Int>()
        while(vectorHilosSecundaria.size < 10 && vector.isNotEmpty()){
            vectorHilosSecundaria.add(vector.removeAt(0))
        }
        hilosEjecucion.addAndGet(1)
        pollHilos.submit {
            var media = vectorHilosSecundaria.average()
            println(media)
            mediaHilo.add(media)
            hilosEjecucion.addAndGet(-1)
        }
    }
    pollHilos.shutdown()

    while (!pollHilos.isTerminated){
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
    println("\n")
    println("Tiempo medias: " + mediaHilo)
    println("Suma hilos: " +  mediaHilo.sum())
}


//Fabrica de números entre 0 y 101
fun fabricaNumeros(nums: Int): ArrayList<Int> {
    var listNums = ArrayList<Int> ()
    repeat(nums){listNums.add(Random.nextInt(0,101))}
    return  listNums
}