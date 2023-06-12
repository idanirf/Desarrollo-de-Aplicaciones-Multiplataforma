import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val hamburguesas = listOf("Whopper 🐷", "Crispy Chicken 🍗", "Veggie Burger 🍃")

    val flujoHamburguesas: Flow<String> = crearFlujoHamburguesas(hamburguesas)

    launch {
        flujoHamburguesas.collect { hamburguesa ->
            println("¡$hamburguesa del Burger King listo para el reparto!")
        }
    }

    println("Iniciando el reparto de hamburguesas del Burger King...")
    iniciarReparto(flujoHamburguesas)
    // Una persona solo coje la hamburguesa de bacon
    flujoHamburguesas.filter {
        it.contains("Whopper")
    }.collect{
        println("Yo solo cojo $it")
    }
    println()
    // Una persona solo coje las dos primeras hamburguesas
    flujoHamburguesas.take(2).collect{
        println("Yo solo cojo dos $it")
    }


    println("\nReparto de hamburguesas del Burger King finalizado.")
}

fun crearFlujoHamburguesas(hamburguesas: List<String>): Flow<String> = flow {
    for (hamburguesa in hamburguesas) {
        delay(2000)
        emit(hamburguesa)
    }
}

suspend fun iniciarReparto(flujoHamburguesas: Flow<String>) {
    for (i in 1..2) {
        delay(3000)
        flujoHamburguesas.collect { hamburguesa ->
            println("Entregando $hamburguesa del Burger King 🍔")
        }
        println()
    }
}

