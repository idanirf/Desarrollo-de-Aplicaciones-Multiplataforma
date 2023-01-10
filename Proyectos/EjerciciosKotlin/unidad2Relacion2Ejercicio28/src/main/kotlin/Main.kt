fun main(args: Array<String>) {
    println("pedir números que estén entre 1 y 5, sí es superior a 5 salir")

    var number: Int

    do {
        println("Introduce un número: ")
        number = readLine()?.toInt() as Int
        if (number >=1 && number <= 5) {
            number
        }
    }while (number >=1 && number <= 5)


}