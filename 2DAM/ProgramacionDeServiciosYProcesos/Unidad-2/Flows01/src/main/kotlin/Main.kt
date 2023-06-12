import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val participantesChicas = listOf("Estafanía", "Lucía")
    val participantesChicos = listOf("Christofer", "Lobo")

    val participantFlowChicas = flow<String> {
        for (participantesChicas in participantesChicas) {
            delay(2000)
            emit(participantesChicas)
        }
    }

    val participantFlowChicos = flow<String> {
        for (participantesChicos in participantesChicos) {
            delay(2000)
            emit(participantesChicos)
        }
    }

    var flowTotal = merge(participantFlowChicos, participantFlowChicas)


    flowTotal.collect { participant ->
        println("¡Bienvenido(a) $participant a La Isla de las Tentaciones🍎💘!")
    }
}


