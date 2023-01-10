fun main() {
    println("Calcular area y perimetro de un cuadrado")
    print("Dime el tamaño del lado: ")
     var tamano: Int
    tamano = readLine()?.toInt() as Int
    var perimetro: Int = calcluloTamano(tamano)
    println("El perimetro es: $perimetro m")
    var area: Int = calculoArea(tamano)
    println("El area de un cuadrado es: $area m2")
}

fun calcluloTamano (tamano: Int): Int {
    return tamano*2
}

fun calculoArea (tamano: Int):Int{
    return Math.pow(tamano.toDouble(), 2.0)?.toInt()
}