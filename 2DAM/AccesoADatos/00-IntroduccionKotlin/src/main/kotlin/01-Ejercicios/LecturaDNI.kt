fun main() {
    val expresionDNI = Regex("^[0-9]{8}[A-Z]$")
    print("Introduce tu DNI: ")
    val dni = readLine()!!.toString()
    if (expresionDNI.matches(dni)) {
        println("El DNI introducido es correcto ✔")
    } else {
        println("El DNI introducido es incorrecto ❌")
    }
}