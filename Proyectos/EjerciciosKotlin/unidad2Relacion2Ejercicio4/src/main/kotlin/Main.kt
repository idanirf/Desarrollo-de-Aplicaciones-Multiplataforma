fun main(args: Array<String>) {
    println("Calcular la media de tres números")
    println("Dime el primer numero")
    var numberOne: Double = readLine()?.toDouble() as Double
    println("Dime el segundo número")
    var numberThow: Double = readLine()?.toDouble() as Double
    println("Dime el tercer número")
    var numberThree: Double = readLine()?.toDouble() as Double
    var media: Double = mediaNumber(numberOne, numberThow, numberThree)
    println("La media de las notas es: $media")
}

fun mediaNumber (numberOne:Double, numberThow:Double, numberThree:Double):Double{
    return (numberOne+numberThow+numberThree)/3
}