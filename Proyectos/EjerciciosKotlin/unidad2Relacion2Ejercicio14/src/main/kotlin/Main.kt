fun main(args: Array<String>) {
    println("Cuantas cifras tiene un número")
    println("¿Introduce el número para comprobar cuantas cifras tiene?")
    var numero:Int = readLine()?.toInt() as Int

    while (numero >=10){
        numero= numero%10
        println("El resultado es: $numero")
    }
}
