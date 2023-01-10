fun main(args: Array<String>) {
    println("Calcular precio del billete de un tren")
    println("¿Qué distancia tienes hasta el destino?")
    var distanciaDestino: Int = readLine()?.toInt() as Int
    println("¿Cuántos días va estar?")
    var diasEstancia: Int = readLine()?.toInt() as Int
    var precioBillete: Int


    if (distanciaDestino > 800 && diasEstancia > 7)
        precioBillete = (distanciaDestino*1.75).toInt()
        else
            precioBillete = (distanciaDestino*2.5).toInt()

    println("El precio del billete es de : $precioBillete €")


}