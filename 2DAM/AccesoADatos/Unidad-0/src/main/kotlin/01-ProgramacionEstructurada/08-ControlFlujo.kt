package `01-ProgramacionEstructurada`

fun main(){
    //IF - ELSEIF
    val num = 2
    if (num % 2 == 0) {
        println("Es par")
    } else{
        println("No es par")
    }

    //En Kotlin no existe el operador ternario, usamos if como una expresión
    val exitExpresion = if (num % 2 == 0) "Es par" else "Es impar"
    println(exitExpresion)

    // WHEN -> Realizar comprobaciones
    when (num % 2) {
        0 -> println("Es par")
        1 -> println("Es impar")
        else -> println("Ninguna de las anteriores")
    }

    // WHEN -> Como expresión
    val exit = when{
        num % 2 == 0 -> "Es par"
        num == 1 -> "Es impar"
        else -> "Ninguna de las anteriores"
    }

    // WHILE
    var data = 0
    while (data < 10) {
        println(data)
        data+=1
    }

    //DO WHILE
    var data1 = 0
    do {
        println(data1)
        data1 += 1
    } while (data1 < 10)

    //FOR -> Rangos
    for (i in 1..20){
        println(i)
    }

    for (i in 1..20 step 2){
        println(i)
    }

        // ---- Muchas más opciones con el for ---- //

    // FOR EACH
    val enteros = IntArray(10)
    for (i in enteros) {
        println(i)
    }

    // REPEAT -> Repite algo todas las veces que sea necesario.
    repeat(10){
        println("Hola DAM")
    }

    //Listas, array, mapas, conjuntos funcionan con las mismas expresiones, no hay diferenciación
}