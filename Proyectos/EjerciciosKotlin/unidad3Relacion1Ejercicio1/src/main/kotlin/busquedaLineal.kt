fun main(args: Array<String>) {
    val vectorEnteros = intArrayOf(2, 4, 89, 23, 12)
    val valorBuscado: Int = 4
    val resultado:Int = busquedaElementoLineal(vectorEnteros, valorBuscado)
    println("La posición en la que se encuentra el elemento buscado es: $resultado")
}

 fun busquedaElementoLineal(vectorEnteros: IntArray, valorBuscado: Int): Int {
    var indice = 0
    var encontrado = false
    var i = 0
    while (i < vectorEnteros.size && !encontrado) {
        if (valorBuscado == vectorEnteros[i]) {
            indice = i
            encontrado = true
        }
        i++
    }
    return indice+1
}