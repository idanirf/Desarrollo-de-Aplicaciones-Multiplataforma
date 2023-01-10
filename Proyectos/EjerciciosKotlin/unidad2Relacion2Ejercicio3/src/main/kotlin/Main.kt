fun main(args: Array<String>) {
    //Declaración de variables
    val NUMBER_PI: Double = 3.141516
    println("Calcular area y perimetro de una circunferencia")
    println("Dime el radio de la circunferencia")
    var radio: Int = readLine()?.toInt() as Int
    var area: Double = areaCircunferencia(radio, NUMBER_PI)
    var perimetro: Double = perimetroCircunferencia(radio, NUMBER_PI)
    println("El area de la circunferencia es $area")
    println("El perimetro de la circunferencia es $perimetro")

}

fun areaCircunferencia(radio: Int, NUMBER_PI: Double): Double {
    return Math.pow(radio.toDouble(), 2.0).toInt() * NUMBER_PI
}

fun perimetroCircunferencia(radio: Int, NUMBER_PI: Double): Double {
    return 2*NUMBER_PI*radio
}