fun main(args: Array<String>) {
    println("Calcula la media de 10 números")

    println("Introduce por teclado el primer número")
    var numnberOne = readLine()?.toInt() as Int

    println("Introduce por teclado el segundo número")
    var numnberTwo = readLine()?.toInt() as Int

    println("Introduce por teclado el tercer número")
    var numnberTheree = readLine()?.toInt() as Int

    println("Introduce por teclado el cuarto número")
    var numnberFour = readLine()?.toInt() as Int

    println("Introduce por teclado el quinto número")
    var numnberFive = readLine()?.toInt() as Int

    println("Introduce por teclado el sexto número")
    var numnberSix = readLine()?.toInt() as Int

    println("Introduce por teclado el séptimo número")
    var numnberSeventh = readLine()?.toInt() as Int

    println("Introduce por teclado el octavo número")
    var numnberEight = readLine()?.toInt() as Int
    
    println("Introduce por teclado el noveno número")
    var numnberNine = readLine()?.toInt() as Int

    println("Introduce por teclado el décimo número")
    var numnberTen = readLine()?.toInt() as Int

    var media: Int

    media = ((numnberOne+numnberTwo+numnberTheree+numnberFour+numnberFive+numnberSix+numnberSeventh+numnberEight+numnberNine+numnberTen)/10)
    println("La media de los 10 números introducidos por teclado es: $media")




}


