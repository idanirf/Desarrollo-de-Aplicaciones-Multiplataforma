
fun main(args: Array<String>) {
    println("Ecuación de segundo grado, con dos resultados")
    println("")
    println("Introduce el valor de (a)")
    var a:Double = readLine()?.toDouble() as Double
    println("Introduce el valor de (b)")
    var b:Double = readLine()?.toDouble() as Double
    println("Introduce el valor de (c)")
    var c:Double = readLine()?.toDouble() as Double

    var solucionA: Double =((-b) + Math.sqrt(Math.pow(b, 2.0) - (4.0 * a * c))) / (2.0 * a)
    var solucionB: Double = ((b) + Math.sqrt(Math.pow(b, 2.0) - (4.0 * a * c))) / (2.0 * a)

    println("La solución de A: $solucionA")
    println("La solución de B: $solucionB")

}