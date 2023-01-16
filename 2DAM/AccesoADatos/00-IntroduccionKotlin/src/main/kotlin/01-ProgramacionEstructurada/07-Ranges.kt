package `01-ProgramacionEstructurada`

fun main() {
    //No se pueden hacer rangos con flotantes, con enteros sí
    //Enteros aleatorios entre 1 y 20
    val rgo1 = 1..20
    // Enteros entre 1 y 20 con salto de 2
    val rgo2 = 1..20 step 2
    // Enteros entre 20 y 1, descendente
    val rgo3 = 20 downTo 1
    // Enteros entre 20 y 1 con salto de 2, descendente
    val rgo4 = 10 downTo 1 step 2
    // Rango de caracteres entre a y z
    val rgo5 = 'a'..'z'
}