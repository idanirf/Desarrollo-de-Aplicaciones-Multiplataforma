import java.net.ServerSocket
import java.net.Socket

fun main() {
    println("####### SERVIDOR ######")
    val PUERTO: Int = 5656
    val serverSocket: ServerSocket?
    var cliente: Socket?
    var numeroCliente: Int = 0

    try {
        serverSocket = ServerSocket(PUERTO)
        do {
            cliente = serverSocket.accept()
            println("Cliente: $cliente")
            numeroCliente++
            var hilo = Hilo(numeroCliente.toInt(), cliente)
            hilo.start()
        } while (true)


    } catch (e: Exception) {
        println(e.message)
    }
}