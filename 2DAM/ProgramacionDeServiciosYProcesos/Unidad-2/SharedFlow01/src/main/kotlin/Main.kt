import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val hamburguesas = listOf("Whopper 🐷", "Crispy Chicken 🍗", "Veggie Burger 🍃")

    val stateFlujoHamburguesas: MutableSharedFlow<String?> = MutableSharedFlow(1,0,BufferOverflow.DROP_OLDEST)

    val job = launch {
        for (hamburguesa in hamburguesas) {
            delay(1000)
            stateFlujoHamburguesas.emit(hamburguesa)
        }
        delay(1000)
        stateFlujoHamburguesas.emit("finish")
    }

    val job1 = launch {
        stateFlujoHamburguesas.takeWhile {
            it!= "finish"
        }.filter {
            it!= null
        }.collect{
            if (it != null) {
                if (it.contains("finish")) {
                    return@collect
                }
            }
            println("¡$it del Burger King listo para el reparto Moto!")
        }
    }

    val job2 = launch {
        delay(2000)
        stateFlujoHamburguesas.takeWhile {
            it!= "finish"
        }.filter {
            it!= null
        }.collect{
            if (it != null) {
                if (it.contains("finish")) {
                    return@collect
                }
            }
            println("¡$it del Burguer King listo para el reparto Bici!")
        }
    }

    val job3 = launch {
        stateFlujoHamburguesas.takeWhile {
            it!= "finish"
        }.filter {
            it!= null
        }.collect{
            if (it != null) {
                if (it.contains("finish")) {
                    return@collect
                }
            }
            println("¡$it del Burguer King listo para el reparto Coche!")
        }
    }

    println("Iniciando el reparto de hamburguesas del Burger King...")

    job.join()
    job1.join()
    job2.join()
    job3.join()

    println("\nReparto de hamburguesas del Burger King finalizado.")
}