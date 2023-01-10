fun main(args: Array<String>) {
    println("Calculo salarios empleados de una fábrica")
    val horasDiurno:Int = 20
    val horasNocturno:Int = 35
    val horasDiurnoDomingo:Int = 30
    val horasNocturnoDomingo:Int = 50
    println("¿Trabajaste de día o de noche?///--- 1 == DIA ----- 2 == NOCHE")
    var turno:Int = readLine()?.toInt() as Int
    println("¿Trabaste el domingo? ///---1 == SI ----- 2 == NO")
    var domingo:Int = readLine()?.toInt() as Int
    println("¿Cuántas horas trabajaste?: ")
    var horasTrabajdas:Int = readLine()?.toInt() as Int
    var resultado:Int = 0


    //DESARROLLO DEL PROGRAMA
    if (turno == 1){
       resultado = horasDiurno*horasTrabajdas
        println("El salario a cobrar es: $resultado")
    } else if (turno == 2) {
        resultado = horasNocturno*horasTrabajdas
        println("El salario a cobrar es: $resultado")
    } else if (domingo == 1 && turno == 1) {
        resultado = horasDiurnoDomingo * horasTrabajdas
        println("El salario a cobrar es: $resultado")
    } else if (domingo == 2 && turno == 2){
        resultado = horasNocturnoDomingo * horasTrabajdas
        println("El salario a cobrar es: $resultado")
    }

}