package `01-ProgramacionEstructurada`

fun main(){
    val valor = miFuncion2()
    println(valor)


    // Parametros con valores por defecto
    println(parametros("Pepe"))
    println(parametros("Pepe", 23))
    println(parametros("Pepe", 23, true))

    // Parametros nombrados
    println(parametros(nombre = "Pedro", edad = 25, repetidor = false))

    // Numero variable de parámetros
    println(calificacion("Pepe", 7.0))
    println(calificacion("Pepe", 7.0, 8.5))
    println(calificacion("Pepe", 7.0, 8.5, 9.0))
    var notas = DoubleArray(10)


    println(calificacion("Pepe", *notas))

    val (a, b) = notas // Desestructuración de datos, primer y segundo elemento

    val (c, _, _, f) = notas // Desestructuración de datos, primer y cuarto

}
fun miFuncion2() = "Esto es una expression Body"



// Parametros con valores por defecto
fun parametros(nombre: String, edad: Int = 18, repetidor: Boolean = false): String {
    return "$nombre $edad $repetidor"
}

// Numero indeterminado de parametros
fun calificacion(nombre: String, vararg notas: Double): String {
    // for(i in notas)
    return "$nombre $notas"
}


