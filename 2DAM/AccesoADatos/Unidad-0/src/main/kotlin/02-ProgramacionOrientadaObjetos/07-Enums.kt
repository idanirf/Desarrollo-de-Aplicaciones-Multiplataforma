package `02-ProgramacionOrientadaObjetos`

/*
 * Clases que nos permite crear Enumns
 * Puede tener métodos y atributos
 *
 * Sealed: permiten que una clase tenga sublclases, pero no pueden ser definidas fuera de la clase padre, por ejemplo
 * para gestionar los errores con una función log.
 */

enum class Meses{
    ENERO, FEBRERO, MARZO, ABRIL, MAYO, JUNIO, JULIO, AGOSTO, SEPTIEMBRE, OCTUBRE, NOVIEMBRE, DICIEMBRE
}

//También tenemos la opcion con el enum de asignarle por ejemplo a cada mes un código.
enum class MesesCode(val codigoMes: Int) {
    ENERO(1), FEBRERO(2), MARZO(3), ABRIL(4), MAYO(5),
    JUNIO(6), JULIO(7), AGOSTO(8), SEPTIEMBRE(9), OCTUBRE(10),
    NOVIEMBRE(11), DICIEMBRE(12)
}

sealed class ErrorGenerated
sealed interface Logger
class Error404(val code: String): ErrorGenerated()
class Error401(val code: String): ErrorGenerated()
class Error500(val code: String): ErrorGenerated(), Logger

fun logs(e: ErrorGenerated) = when (e){
    is Error404 -> println("Error404: ${e.code}")
    is Error401 -> println("Error401: ${e.code}")
    is Error500 -> println("Error500: ${e.code}")
}
