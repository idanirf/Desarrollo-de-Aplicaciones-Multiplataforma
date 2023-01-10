fun main(args: Array<String>) {
    println("Muestra los números impares comprendidos entre 1 y 20. Y la suma de todos ellos.")
    var addNumbers: Int = 0

    for (i in 1..1000) {
        if (i % 2 == 0) {
            addNumbers = (addNumbers + i)
            println("Es número impar: $i")
        }
    }
    println("La suma de los números comprendidos entre 1 y 1000 son: $addNumbers")
}