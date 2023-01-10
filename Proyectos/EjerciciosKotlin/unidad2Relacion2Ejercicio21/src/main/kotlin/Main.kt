fun main(args: Array<String>) {
    println("Muestra los números pares que estén entre 1 y 20 :)")
    for (i in 1..20)
        if (i%2==0)
            println("Los números pares comprendidos ente 1 y 20 son: $i")
}