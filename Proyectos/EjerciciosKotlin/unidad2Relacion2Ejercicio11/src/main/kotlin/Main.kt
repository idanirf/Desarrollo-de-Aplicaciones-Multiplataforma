fun main(args: Array<String>) {
    println("Comprobar si los números introducidos están ordenados")
    println("Introduce el primer número")
    var numberOne:Int = readLine()?.toInt() as Int
    println("Introduce el segundo número")
    var numberTwo:Int = readLine()?.toInt() as Int
    println("Introduce el tercer número")
    var numberThree:Int = readLine()?.toInt() as Int

    if(numberOne<= numberTwo && numberTwo<=numberThree)
        println("Los números que has introducido están ordenados")
    else
        println("Los números que has introducido no están ordenados")


}