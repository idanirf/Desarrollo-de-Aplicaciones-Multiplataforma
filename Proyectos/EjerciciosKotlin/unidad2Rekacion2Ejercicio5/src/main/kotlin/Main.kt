fun main(args: Array<String>) {
    println("Determinar sí un número es positivo o negativo")
    println("Dime el número a comprobar: ")
    var number: Double = readLine()?.toDouble() as Double
    isPositive(number)

}

fun isPositive(number: Double){
    if(number>=0){
        println("El número es positivo")
    } else {
        println("El número es negativo")
    }
}