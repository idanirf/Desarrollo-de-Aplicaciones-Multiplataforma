package `01-ProgramacionEstructurada`

fun main(){
    //Pedir la edad
    var edad = 0
    do{
        println("Dime tu edad:")
        edad = readln().toIntOrNull() ?: -1
    } while (edad < 0)

    //Posibilidad de leer argumentos, si los cogemos en la fun main.

}