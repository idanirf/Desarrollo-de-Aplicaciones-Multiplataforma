fun main(args: Array<String>) {
    println("Imprimir vector")
    val vectorEnteros = intArrayOf(2,4,89,23,12)

    val valueMinimo = vectorEnteros.minOrNull()
    println("El valor mínimo del vector es: $valueMinimo")
}