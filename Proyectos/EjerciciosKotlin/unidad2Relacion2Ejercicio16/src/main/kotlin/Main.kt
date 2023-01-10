fun main(args: Array<String>) {
    println("Reloj que nos pide la hora y nos aumenta 1minuto y 1segundo")
    println("Introduce la hora: ")
    var hora:Int = readLine()?.toInt() as Int
    println("Introduce los minutos: ")
    var minutos:Int = readLine()?.toInt() as Int
    println("Introduce los segundos: ")
    var segundo:Int = readLine()?.toInt() as Int

    segundo++
    if (segundo >=59) {
        minutos++
        segundo = segundo * 0
    }
    if (minutos >=60) {
        hora++
        minutos = minutos * 0
    }
    if (hora >=24) {
        hora = hora * 0
    }
    println("La hora actualizada es: $hora : $minutos : $segundo")
}