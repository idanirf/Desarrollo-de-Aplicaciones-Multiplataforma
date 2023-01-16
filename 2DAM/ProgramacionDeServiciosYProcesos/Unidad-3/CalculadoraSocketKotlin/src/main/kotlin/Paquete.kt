import java.io.Serializable

class Paquete(var numero1: Int?, var numero2: Int?, var operacion: String) : Serializable {
    override fun toString(): String {
        return "Paquete(numero1=$numero1, numero2=$numero2, operacion='$operacion')"
    }
}