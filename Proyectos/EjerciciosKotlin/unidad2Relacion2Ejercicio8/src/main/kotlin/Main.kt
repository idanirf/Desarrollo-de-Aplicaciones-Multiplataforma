fun main(args: Array<String>) {
    println("Avisar sí hay decimales en un número")
    println("Introduce el número: ")
    var number: Double = readLine()?.toDouble() as Double
    if ((number % 1) == 0.0){
        println("El número NO tiene decimales")
    } else {
        println("El numero SI tiene decimales")
    }

}