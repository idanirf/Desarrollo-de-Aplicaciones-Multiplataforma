package `02-ProgramacionOrientadaObjetos`

/*
 * Permite crear un alias para un dato que tengamos en nuestro código
 */

typealias MiEntero = Int
typealias Matriz = Array<IntArray>
typealias Columna = IntArray
typealias NoRepetidos = Set<Int>

fun main(){
    val miEntero: MiEntero = 10
    println(miEntero)
    val matriz: Matriz = arrayOf(intArrayOf(1, 2,3), intArrayOf(4, 5, 6))
    println(matriz)
    val noRepetidos: NoRepetidos = setOf(1,2,3,4,5,6)
}