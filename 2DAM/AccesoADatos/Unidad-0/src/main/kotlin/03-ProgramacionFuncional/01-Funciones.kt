package `03-ProgramacionFuncional`

/*
 * Las funciones son ciudadanos de primera clase
 * TODO completar con apuntes de JL
 */

fun suma(a: Int, b:Int): Int{
    return a + b
}

val resta = fun(a:Int, b:Int):Int {
    return a - b
}

fun funcionConFuncion(a:Int, b:Int, funcion:(Int, Int)->Int):Int {
    return funcion(a, b)
}

fun funcionQueDevuelveFuncion(): (Int, Int) -> Int {
    return resta
}

//Funcion lambda
var resta1 = { a:Int, b:Int -> a - b }

//Multiplicacion de la lambda
//println(funcionConParamentrosFucnion(1,2,{a,b ->a*b}))

fun main(){
    val miSuma = ::suma
    println(suma(1,2))
    println(miSuma(1,2))
    println(funcionConFuncion(1,2, resta))
    println(funcionConFuncion(1,2, ::suma))
    println(funcionConFuncion(1,2, miSuma))

}