fun main(args: Array<String>) {
    println("Restar al mayor el número menor")
    var resultado:Int
    println("Introduce el número A: ")
    var numberA: Int = readLine()?.toInt() as Int
    println("Introduce el número B: ")
    var numberB: Int = readLine()?.toInt() as Int

    if (numberA < numberB){
       resultado = numberB - numberA
        println("El resultado es $resultado")
    } else {
        resultado = numberA - numberB
        println(" El resultado es $resultado")
    }
}