fun main(args: Array<String>) {
    println("Programa que pide números hasta que se introduzca uno mayor que 100")

    var number:Int


    do {
        println("Introduce un número: ")
        number = readLine()?.toInt() as Int
        if (number <=100)
            number
    } while (number <=100)


}