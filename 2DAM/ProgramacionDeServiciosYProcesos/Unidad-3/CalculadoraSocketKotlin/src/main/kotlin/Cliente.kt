import java.io.DataInputStream
import java.io.ObjectOutputStream
import java.net.InetAddress
import java.net.Socket

fun main() {
    println("CLIENTE CALCULATOR")
    val direccion: InetAddress
    val servidor: Socket
    val PUERTO: Int = 5656

    direccion = InetAddress.getLocalHost()
    servidor = Socket(direccion, PUERTO)

    //Obtenemos datos para empaquetar y enviar al servidor
    println("A continuación debe introducir los números y el tipo de operación que desea realizar")
    println("Introduce el número 1:")
    val numero1: Int? = readln().toIntOrNull()
    println("Introduce el número 2:")
    val numero2: Int? = readln().toIntOrNull()
    println("Introduce la operación que desea realizar:")
    println("SUMA/RESTA/MULTIPLICACION/DIVISION")
    val operacion: String? = readlnOrNull()

    try {
        require(numero1 != null && numero2 != null && operacion != null) { "No se ha introducido un número o la operación no es válida" }
        val paquete = Paquete(numero1, numero2, operacion)
        val salidaDatos = ObjectOutputStream(servidor.getOutputStream())
        salidaDatos.writeObject(paquete)


    } catch (e: Exception) {
        println(e.message)
    }
    //Recibimos el resultado del servidor
    val entradaDatos = DataInputStream(servidor.getInputStream())
    println(entradaDatos.readUTF())

}