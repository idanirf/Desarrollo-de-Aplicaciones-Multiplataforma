fun main(args: Array<String>) {
    println("Imprimir vector")
    val vectorEnteros = intArrayOf(2,4,89,23,12)

    //Ordenación por inserción
    var temporal = 0
    for(i in (0 until vectorEnteros.size)){
        temporal = vectorEnteros[i]
        var j: Int = i-1
        while ((j>=0)&&(temporal<vectorEnteros[j])) {
            vectorEnteros[j + 1] = vectorEnteros[j]
            j--
        }
        vectorEnteros[j+1] = temporal

    }
    println("El vector ordenado es: ")
    for (i in (0 until vectorEnteros.size)){
        print(vectorEnteros[i])
        print(", ")
    }
}