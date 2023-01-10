fun main(args: Array<String>) {
    println("Conversor de temperaturas / Farenheit y Celsious")
    println("¿ A que unidad de temperatura quieres convertir: (1 - Farenheit) (2 - Celsious) ?")
    var conversor:Int = readLine()?.toInt() as Int
    var farenheit:Int
    var celsious:Int

    if (conversor == 1){
        println("Introduce los daros en CELSIOUS")
        celsious = readLine()?.toInt() as Int
        farenheit = (celsious*9/5)+32
        println("La temperatura en Farenheit es: $farenheit")
    } else if (conversor == 2){
        println("Introduce la temperatura en FARENHAIT")
        farenheit = readLine()?.toInt() as Int
        celsious = (((farenheit - 32)*5)+32)
        println("La temperatura en Celsious es: $celsious")



    }


}