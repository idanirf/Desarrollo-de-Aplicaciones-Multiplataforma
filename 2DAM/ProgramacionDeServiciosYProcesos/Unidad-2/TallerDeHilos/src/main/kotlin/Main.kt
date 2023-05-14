import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    class Coche(
        var tReparacion: Int = Random.nextInt(3,300),
        var precio: Int = Random.nextInt(50,250)
    )

    class Mecanico(
        var listadoCoches: ArrayList<Coche> = ArrayList<Coche>()
    )
    // Listado de mecanicos y coches

    var listaCoches1 = ArrayList<Coche>()
    repeat(Random.nextInt(1,5)) {
        listaCoches1.add(Coche())
    }
    var listaCoches2 = ArrayList<Coche>()
    repeat(Random.nextInt(1,5)) {
        listaCoches2.add(Coche())
    }
    var listaCoches3 = ArrayList<Coche>()
    repeat(Random.nextInt(1,5)) {
        listaCoches3.add(Coche())
    }

    var mecanico1 = Mecanico(ArrayList(listaCoches1))
    var mecanico2 = Mecanico(ArrayList(listaCoches2))
    var mecanico3 = Mecanico(ArrayList(listaCoches3))

    // Precios y tiempos por mecanico

    var precioTotal: Int = 0
    var tiempoTotal: Int = 0
    var precioMecanico1: Int = 0
    var precioMecanico2: Int = 0
    var precioMecanico3: Int = 0
    var tiempoPorHilos = measureTimeMillis {


        // Numero de hilos que tiene nuestro programa
        val poll = Executors.newFixedThreadPool(3)

        // Tiempo inicial de ejecución
        val tInicial = System.currentTimeMillis()

        // Primer Poll - Poll01
        poll.submit {
            var precioMecanico01: Int = 0
            mecanico1.listadoCoches.forEach {
                Thread.sleep(it.tReparacion.toLong())
                precioMecanico01 += it.precio
            }
            precioMecanico1 = precioMecanico01
        }

        // Segundo Poll - Poll02
        poll.submit {
            var precioMecanico02: Int = 0
            mecanico2.listadoCoches.forEach {
                Thread.sleep(it.tReparacion.toLong())
                precioMecanico02 += it.precio
            }
            precioMecanico2 = precioMecanico02
        }

        // Tercer Poll - Poll03
        poll.submit {
            var precioMecanico03: Int = 0
            mecanico3.listadoCoches.forEach {
                Thread.sleep(it.tReparacion.toLong())
                precioMecanico03 += it.precio
            }
            precioMecanico3 = precioMecanico03
        }


        poll.shutdown()
        poll.awaitTermination(3000L, TimeUnit.MILLISECONDS)


        precioTotal = precioMecanico1 + precioMecanico2 + precioMecanico3
        println("Precio total: $precioTotal €")
    }
    println("Tiempo total: $tiempoPorHilos ms")
}