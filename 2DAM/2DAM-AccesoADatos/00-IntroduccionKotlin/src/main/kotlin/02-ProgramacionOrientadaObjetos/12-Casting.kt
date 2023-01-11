package `02-ProgramacionOrientadaObjetos`

/*
 * El casting va con as
 * Si no se puede exception
 */

fun main(args: Array<String>){
    val a: Any = "Hola"
    val b: String = a as String
    //println(a)
    println(b)

    val c: Any = 1
    val d: String? = c as? String
    //Null casting con un null
    println(d)

}