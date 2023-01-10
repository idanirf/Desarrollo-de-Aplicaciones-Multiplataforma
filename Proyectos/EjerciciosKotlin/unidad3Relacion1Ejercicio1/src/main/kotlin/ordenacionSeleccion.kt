fun main(args: Array<String>) {
    println("Imprimir vector")
    val vectorEnteros = intArrayOf(2,4,89,23,12)

    //Ordenación por selección
    var temporal = 0
    var posicionMinima: Int
    for(i in (0 until vectorEnteros.size -1)){
        posicionMinima = i
        for(j in (i+1 until vectorEnteros.size)){
            if (vectorEnteros[j]<vectorEnteros[posicionMinima]){
                posicionMinima = j
                temporal = vectorEnteros[i]
                vectorEnteros[i] = vectorEnteros[posicionMinima]
                vectorEnteros[posicionMinima] = temporal
            }
        }
    }
    println("El vector ordenado es: ")
    for (i in (0 until vectorEnteros.size)){
        print(vectorEnteros[i])
        print(", ")
    }
}