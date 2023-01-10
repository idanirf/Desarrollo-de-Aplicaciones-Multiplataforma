fun main(args: Array<String>) {
    val vectorEnteros = intArrayOf(2, 4, 89, 23, 12)
    quickSort(vectorEnteros, 0, vectorEnteros.size-1)


    print("El vector ordenado por QUICKSORT es: ")
    for(i in vectorEnteros){
        print(" ")
        print(i)
    }
  }

fun quickSort(vectorEnteros: IntArray, posicionIzquierda: Int, posicionDerecha: Int) {
    val i = particion (vectorEnteros, posicionIzquierda, posicionDerecha)
    if(posicionIzquierda < i-1) {
        quickSort(vectorEnteros, posicionIzquierda, i-1)
    }
    if(i < posicionDerecha) {
        quickSort(vectorEnteros,i, posicionDerecha)
    }
}

fun particion(vectorEnteros: IntArray, posicionIzquierda: Int, posicionDerecha: Int): Int {
    var izquierda = posicionIzquierda
    var derecha = posicionDerecha
    val pivote = vectorEnteros[(izquierda + derecha)/2]
    while (izquierda <= derecha) {
        while (vectorEnteros[izquierda] < pivote)
            izquierda++
        while (vectorEnteros[derecha] > pivote)
            derecha--

        if (izquierda <= derecha) {
            vectorIntercambio(vectorEnteros, izquierda,derecha)
            izquierda++
            derecha--
        }
    }
    return izquierda
}

fun vectorIntercambio(a: IntArray, b: Int, c: Int) {
    val temporal = a[b]
    a[b] = a[c]
    a[c] = temporal
}