package `01-ProgramacionEstructurada`

/*
 * Aquello que va con var se puede leer y escribir.
 * Aquello que va con val solo se puede leer en ningún momento se puede escribir.
 * !!!CONSEJO¡¡¡ Que sea to a val.
 */

//Declarar la constante
const val PI = 3.1415
fun main(){
    var edad = 20
    edad = 21
    println("Tu edad es: $edad")

    val nombre = "Dani"
    //nombre = "Dani" -> genera un error
}