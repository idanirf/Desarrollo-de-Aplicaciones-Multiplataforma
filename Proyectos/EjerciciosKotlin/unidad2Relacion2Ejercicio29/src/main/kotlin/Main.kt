fun main(args: Array<String>) {
    println("Notas alumnos con resultados de aproados y suspensos")

    var aprobados:Int = 0
    var notas:Int
    var suspensos: Int = 0
    var alumnos: Int = 30
    var totalNotas: Int = 0

    for (i in 0..30) {
        println("Introduce una nota mediante teclado_ ")
        notas = readLine()?.toInt() as Int
        if (notas >= 0 && notas <= 4) {
            println("Nota almacenada como INSUFICIENTE")
            suspensos++
        } else if (notas>=5 && notas<6){
            println("Nota almacenada como SUFICIENTE")
            aprobados++
        } else if (notas ==6){
            println("Nota almacenada como BIEN")
            aprobados++
        } else if (notas >=7 && notas <=9){
            println("Nota almacenada como NOTABLE")
            aprobados++
        } else if (notas >=9 && notas<=10){
            println("Nota almacenada como SOBRESALIENTE")
            aprobados++
        } else if (notas <0){
            totalNotas = totalNotas + notas
        }
    }

    totalNotas= totalNotas/alumnos
    println("Total de alumnos aprobados $aprobados")
    println("Total de alumnos suspensos $suspensos")
    println("Total nota media del grupo de alumnos es de: $totalNotas")
}