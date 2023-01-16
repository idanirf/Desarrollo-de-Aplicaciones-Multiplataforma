package `01-ProgramacionEstructurada`

fun main(){
    //Podemos indicar el tipo de dato o no, no es necesario indicarlo
    val entero = 20
    val decimal = 3.2
    val cadena = "Hola mundo"
    val boolean = false
    val caracter = 'a'

    //Tenemos que indicar el tipo de casting que queremos hacer de forma explícita
    val castingEntero = entero.toLong()
    println("Cast: $castingEntero")

}