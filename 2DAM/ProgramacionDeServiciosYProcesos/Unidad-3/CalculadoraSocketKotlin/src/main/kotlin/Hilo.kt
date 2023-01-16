import java.io.DataOutputStream
import java.io.ObjectInputStream
import java.net.Socket

class Hilo : Thread {
    private var numCliente: Int? = 0
    private var cliente: Socket? = null

    constructor(nCliente: Int, sCliente: Socket?) {
        this.numCliente = nCliente
        this.cliente = sCliente
    }

    override fun run() {
        // Paquete de entrada cliente -> servidor
        val paqueteEntrada = ObjectInputStream(cliente?.getInputStream())
        val objeto = paqueteEntrada.readObject() as Paquete

        //Datos del paquete
        val numeroUno = objeto.numero1
        val numeroDos = objeto.numero2
        val operacion = objeto.operacion

        //Operaciones
        var resultado: String? = when (operacion) {
            "SUMA" -> numeroUno?.plus(numeroDos!!).toString()
            "RESTA" -> numeroUno?.minus(numeroDos!!).toString()
            "MULTIPLICACION" -> numeroUno?.times(numeroDos!!).toString()
            "DIVISION" -> numeroUno?.div(numeroDos!!).toString()
            else -> "Operación no válida"
        }

        val salidaPaquete = DataOutputStream(cliente?.getOutputStream())
        salidaPaquete.writeUTF("Numero 1: $numeroUno Número 2: $numeroDos = $resultado")
    }
}