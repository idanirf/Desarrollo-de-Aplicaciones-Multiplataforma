fun main(args: Array<String>) {
    val vectorEnteros = intArrayOf(2, 4, 12, 23, 89)
    val valorBuscado: Int = 12
    val posicionBuscada = busquedaRecursiva(vectorEnteros, valorBuscado)+1
    println("La posicion buscada es $posicionBuscada")
}


fun busquedaRecursiva(vectorEnteros: IntArray, valorBuscado: Int): Int {
    return busquedaRecursiva(vectorEnteros, valorBuscado, 0, vectorEnteros.size - 1)
}

fun busquedaRecursiva(vectorEnteros: IntArray, elemento: Int, posicionInicial: Int, posicionFinal: Int): Int {
    val posicionMedio = (posicionFinal + posicionInicial) / 2
    return if (vectorEnteros[posicionMedio] == elemento) {
        posicionMedio
    } else if (posicionInicial == posicionFinal) {
        -1
    } else if (vectorEnteros[posicionMedio] < elemento) {
        busquedaRecursiva(vectorEnteros, elemento, posicionMedio + 1, posicionFinal)
    } else {
        busquedaRecursiva(vectorEnteros, elemento, posicionInicial, posicionMedio - 1)
    }
}