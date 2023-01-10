fun main(args: Array<String>) {
    println("Calcular el factorial de un número introducido por teclado")
    println("Introduce el número que quieres calcular el factorial: ")
    var number:Int = readLine()?.toInt() as Int
    var factorial:Int = 1
    for (i in 1..number){
        factorial = factorial * i
    }
    println("El factorial del número $number es: $factorial")

}