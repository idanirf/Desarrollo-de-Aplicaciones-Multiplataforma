package `02-ProgramacionOrientadaObjetos`

/*
 * Clases Anidadas
 * Nasted -> no puedo acceder a propiedades/metodos privadpos de la clase externa //Clase dentro de una clase
 * Inner -> no puedo acceder a propiedades/metodos ...
 */

class ClasePrimaria {
    private val id: Int = 1
    var nombre = "Daniel Rodriguez"

    class NastedAnidado{
        fun metodoAnidado() = "Hola Kotlin"
    }
}

class ClasePrimariaB{
    private val id: Int = 1
    inner class InnerAnidado{
        fun metodoAnidado() = id
    }
}

fun main(){
    // Este es el casi de que usemos Nested

    val clasePrimaria = ClasePrimaria()
    val nasted = ClasePrimaria.NastedAnidado()

    println(nasted.metodoAnidado()) // --> Nos imprime el "Hola Kotlin"
    println(ClasePrimaria.NastedAnidado().metodoAnidado())


    //Este es el caso en el que usemos Inner
    val clasePrimariaB = ClasePrimariaB()
    val inner = ClasePrimariaB().InnerAnidado()
    println(inner.metodoAnidado())
    println(clasePrimariaB.InnerAnidado().metodoAnidado())
}
