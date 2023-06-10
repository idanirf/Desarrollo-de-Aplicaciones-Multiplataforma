import java.util.*
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.ReentrantLock

class FabricaBolsos(val nombre: NombreFabrica) {
    private val lock: ReentrantLock = ReentrantLock()
    private val noLleno: Condition = lock.newCondition()
    private val noVacio: Condition = lock.newCondition()
    private val buffer: Queue<Bolso> = LinkedList()
    private val tamMaximo: Int = 10
    private var contadorId: Int = 0

    fun producirBolso(modelo: ModeloBolso) {
        lock.lock()
        try {
            while (buffer.size == tamMaximo) {
                noLleno.await()
            }
            val nuevoBolso = Bolso(generarNuevoId(), modelo)
            buffer.offer(nuevoBolso)
            println("[${nombre.nombre}] Se produjo un nuevo bolso con ID: ${nuevoBolso.id} y modelo: ${nuevoBolso.modelo}")
            noVacio.signalAll()
        } finally {
            lock.unlock()
        }
    }

    fun consumirBolso(nombreTransportista: NombreTransportista) {
        lock.lock()
        try {
            while (buffer.isEmpty()) {
                noVacio.await()
            }
            val bolso = buffer.poll()
            println("[${nombre.nombre}] El transportista ${nombreTransportista.nombre} está entregando el bolso con ID: ${bolso.id}, modelo: ${bolso.modelo} al centro logístico")
            noLleno.signalAll()
        } finally {
            lock.unlock()
        }
    }


    private fun generarNuevoId(): Int {
        contadorId++
        return contadorId
    }
}

enum class NombreFabrica(val nombre: String) {
    MADRID("Fabrica Madrid"),
    VALENCIA("Fabrica Valencia"),
    GRANADA("Fabrica Granada")
}