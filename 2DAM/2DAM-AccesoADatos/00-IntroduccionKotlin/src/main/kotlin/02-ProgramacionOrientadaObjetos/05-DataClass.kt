package `02-ProgramacionOrientadaObjetos`

/*
 * https://kotlinlang.org/docs/data-classes.html
 * El doble igual no es equals // Para ello p.equals(o), es decir si p y o apuntan al mismo objeto
 * El == es el metodo equals
 * El === misma dirección de memoria que es el equivalente del == en java
 * ordenador1.copy -> copia una clase
 */

data class Personaje(
    val id: Int,
    val name: String,
)

fun main() {
    val persona1 = Personaje(1, "Maria")
    val persona2 = Personaje(1, "Maria")

    println(persona1 == persona2)
    println(persona1!= persona2)
    //No apunta a la misma dirección de memoria --> false
    println(persona1 === persona2)
}