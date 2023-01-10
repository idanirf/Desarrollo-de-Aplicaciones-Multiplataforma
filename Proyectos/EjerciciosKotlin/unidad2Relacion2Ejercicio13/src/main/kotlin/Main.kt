fun main(args: Array<String>) {
    println("Calcular cociente y resto mediante restas sucesivas")
    println("Introduce el número que deseas dividir: ")
    var resultado: Int = 0
    var cociente: Int = readLine()?.toInt() as Int
    println("¿Entre cuanto quieres dividir? ")
    var dividendo: Int = readLine()?.toInt() as Int
    var resto: Int = cociente

    //Desarrollo del programa
    while(resto>=dividendo){
        resto = (resto - dividendo)
        resultado = (resultado + 1)
    }
    println("El resultado de la división es: $resultado")
    println("El resto de la división es: $resto")

}