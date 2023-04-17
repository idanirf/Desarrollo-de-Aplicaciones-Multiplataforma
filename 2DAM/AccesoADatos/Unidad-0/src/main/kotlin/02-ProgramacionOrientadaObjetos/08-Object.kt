package `02-ProgramacionOrientadaObjetos`

/*
 * Nos sirve para crear clases anónimas.
 * Puedo crear una clase sobre la marcha
 * Companion Object -> //métodos de clase //Objetos que están compartidos por la clase tanto sus atributos como los métodos.
 * También en patrón singleton. To lo que lleve object es singleton
 */

class CompanionObject {
    companion object{
        var contador: Int = 0
        fun crear(){
            contador++
        }
    }
}

/*
 * ¿Qué es el patrón Factory?
 * Es un patrón de diseño creacional que proporciona una interfaz para crear objetos en una superclase, mientras permite
 *  a las subclases alterar el tipo de objetos que se crearán.
 */

class Personaa(val name: String){
    companion object Factory {
        fun create(): Personaa {
            return Personaa("John")
        }
    }
}


/*
 * ¿Qué es el patrón Singleton?
 * Singleton es un patrón de diseño creacional que nos permite asegurarnos de que una clase tenga una única instancia,
 * a la vez que proporciona un punto de acceso global a dicha instancia.
*/

object Singleton {
    var contador: Int = 0
    fun crear() {
        contador++
    }
}

fun main(){
    val objeto = object {
        var nombre: String = "Objeto 1"
        fun saludo() = println("Hola soy: $nombre")
    }

    objeto.saludo()

}