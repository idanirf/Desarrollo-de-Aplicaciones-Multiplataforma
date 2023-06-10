import java.time.LocalDate
import java.time.LocalDateTime

fun main() {
    val hoy = LocalDate.now()
    val fabricaBolsos = FabricaBolsos()
    val iteraciones = 5
    val productor = Productor(fabricaBolsos, iteraciones)
    val transportista = Transportista(fabricaBolsos, iteraciones)
    productor.start()
    transportista.start()
    productor.join()
    transportista.join()
    println("Turno de: $hoy TERMINADO")
}