fun main(args: Array<String>) {
    println("Superficie y perimetro de un rectangulo")
    println("Dime el tamaño del lado MAYOR")
    var ladoMayor: Int = readLine()?.toInt() as Int
    println("Dime el tamaño del lado MENOR")
    var ladoMenor: Int = readLine()?.toInt() as Int
    var perimetro: Int = calculoPerimetro(ladoMayor, ladoMenor)
    println("El perimetro del rectangulo es: $perimetro m")
    var area: Int = calculoArea(ladoMayor, ladoMenor)
    println("El perimetro del rectangulo es: $area m2")


}

fun calculoPerimetro (ladoMayor: Int, ladoMenor: Int): Int {
    return (ladoMenor *2) + (ladoMayor*2)
}

fun calculoArea (ladoMayor: Int, ladoMenor: Int): Int{
    return ladoMayor * ladoMenor
}