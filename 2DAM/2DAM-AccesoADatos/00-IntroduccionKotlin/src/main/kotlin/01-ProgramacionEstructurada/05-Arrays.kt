package `01-ProgramacionEstructurada`

//Estructura de datos donde se accede por indice

fun main(){
    //Array unidimensional, los usamos cuando no son dinámicos, en caso de ser dinámicos usamos listas, pilas ...
    val array1 = arrayOf(1, 2, 3, 4, 5, 6)
    //Array sin conocer el tamaño, está inicializado a 0
    val array2 = IntArray(5)
    println(array2[1])
    //Array sin conocer el tamaño, está inicializado a 1
    val array3 = IntArray(5){i -> 1 }
    println(array3[1])

    //Array Multidimensional, es decir array de array
    val array4 = Array(3){IntArray(3)}
    println(array4[2][2])
    val array5 = Array(3){IntArray(3){i -> 2 } }
    println(array5[2][1])
}