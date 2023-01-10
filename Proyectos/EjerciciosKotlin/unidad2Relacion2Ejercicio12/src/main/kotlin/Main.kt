fun main(args: Array<String>) {
    println("Comprueba si los números introducidos por teclado son consecutivos")
    println("Introduce el primer número")
    var numberOne:Int = readLine()?.toInt() as Int
    println("Introduce el segundo número")
    var numberTwo:Int = readLine()?.toInt() as Int
    println("Introduce el tercer número")
    var numberThree:Int = readLine()?.toInt() as Int

    if (numberOne +1 == numberTwo && numberTwo +1 == numberThree)
        println("Los números que has introducido son consecutivos")
    else
        println("Los números que has introducido NO son consecutivos")

}