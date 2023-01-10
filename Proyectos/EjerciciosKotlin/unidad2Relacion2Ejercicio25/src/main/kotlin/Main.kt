fun main(args: Array<String>) {
    println("Calcula la media de N-números de forma indefinida, mientras que el número sea mayor que 0")

    var number: Double
    var mediaTotal: Double
    var division: Double = 0.0
    var suma: Double = 0.0

    do{
        println("Introduce por teclado el número")
        number = readLine()?.toDouble() as Double
        if(number>=0)
            suma = suma + number
            division = division + 1
    } while (number>=0)
        mediaTotal = suma / division
        println("La media de los números introducidos es: $mediaTotal")
}