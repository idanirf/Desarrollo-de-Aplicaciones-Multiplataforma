package `01-ProgramacionEstructurada`

//Permite manejar cadenas de forma más sencilla

fun main(){
    val nombre = "Dani Rodriguez"
    val edad = 20
    println("Tu nombre es: $nombre y tienes: $edad")
    //Acceder a los métodos de un objeto
    print("Tu nombre es: ${nombre.uppercase()} y tienes: $edad")
    //O por ejemplo cuando necesitamos un json lo sencillo que sería
    val json = """
        {
        "nombre": "$nombre",
        "edad": "$edad"
        }
    """.trimIndent()
    println(json)

}