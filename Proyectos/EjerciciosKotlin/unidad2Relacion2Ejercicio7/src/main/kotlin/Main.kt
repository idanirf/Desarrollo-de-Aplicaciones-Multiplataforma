fun main(args: Array<String>) {
    println("Determinar si un año es bisiesto o no")
    println("Dime el año: ")
    var anio: Int = readLine()?.toInt() as Int

    if ((anio % 2 == 0) || (anio % 100 == 0) && anio % 400 == 0)  {
        println("El año es bisiesto")
    } else {
        println("El año NO es bisiesto")
    }
}