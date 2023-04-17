package `01-ProgramacionEstructurada`

/*
 * En Kotlin no existe diferencias entre las excepciones Checked y Unchecked.
 * En el caso de que el compilador te obligue a capturar una excepción, capturamos lo que sea necesario
 * Cuando hay una excepción se devuelve el tipo Nothing, en el caso de que haya que informar a Java
 * hay que informar con la anotación @Throws
 * Nothing -> tipo que se devuelve en el cas de devolver una excepción
 * Abajo un ejemplo en Kotlin de excepción.
 */

fun main(){
    val mensaje = "Bienvenido a Kotlin"
    mensaje.toInt()
    try {
        val mensaje = "Bienvenido a Kotlin"
        mensaje.toInt()
    } catch (exception: NumberFormatException){
        println(exception.message)
    }
}