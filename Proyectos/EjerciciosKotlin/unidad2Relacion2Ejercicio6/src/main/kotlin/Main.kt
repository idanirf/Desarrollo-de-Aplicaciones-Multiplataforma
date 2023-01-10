fun main(args: Array<String>) {
    println("Raíz cuadrada de un número siempre que sea positivo, en su defecto avisar.")
    println("¿Introduce el número que desea calcular?")
    var number: Double = readLine()?.toDouble() as Double
    calcularRaiz(number)
}

fun calcularRaiz(number: Double) {
    if (number >=0){
        var resultado:Double = Math.sqrt(number)
        println("El resultado de la raiz es $resultado")
    } else {
        println("Debes introducir un número positivo")
    }
}