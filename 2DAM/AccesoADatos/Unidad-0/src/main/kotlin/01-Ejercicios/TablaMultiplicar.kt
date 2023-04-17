fun main() {
    println("Introduce el numero de la tabla: ")
    val numeroA = readln().toInt()
    println("¿Hasta que número de la tabla?: ")
    var numeroB = readln().toInt()

    for ( i in 0.. numeroB) {
        println("$numeroA x $i = ${numeroA * i}")
    }
}
