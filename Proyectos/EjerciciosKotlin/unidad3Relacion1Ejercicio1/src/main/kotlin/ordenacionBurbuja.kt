fun main(args: Array<String>) {
    println("Imprimir vector")
    val vectorEnteros = intArrayOf(2,4,89,23,12)

    //Ordenación por burbuja
    var temporal = 0
    for(i in (0 until vectorEnteros.size -1)){
        for(j in (0 until vectorEnteros.size - 1)){
            if (vectorEnteros[j]>vectorEnteros[j+1]){
                temporal = vectorEnteros[j]
                vectorEnteros[j] = vectorEnteros[j+1]
                vectorEnteros[j+1] = temporal
            }
        }
    }
    println("El vector ordenado es: ")
        for (i in (0 until vectorEnteros.size)){
            print(vectorEnteros[i])
            print(", ")
        }
}