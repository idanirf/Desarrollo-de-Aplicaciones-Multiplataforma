package `01-ProgramacionEstructurada`

//En Kotlin hay que indicar si una variable es null o no
/*
 Tipos plataforma cuando no sepamos si es null o no null (no sabemos como se puede comportar), estos tipos llevan un (!), ocurre cuando usamos código de
 Java y lo pasamos a Kotlin. Hay que comprobar la libreria sí viene de Java y tomar la decisión.
 Nullable
 NotNull
 Usar las anotaciones de Lombock indicando si es nullable o no, de esta forma también podemos controlar las excepciones,
 en el compilador nos saldrá el error.
 En Kotlin no es necesario usar Lombock
  */
fun main(){
    var nombre: String? = null
    println(nombre)
    nombre = "Dani"
    var apellidos: String = "Rodriguez Fernandez"
    println(apellidos)
    apellidos = nombre

    val edad = 20
    var edad2: Int? = 20
    println(edad2)

    /*
     * Tenemos las llamadas seguras, llamamos a un método de un objeto que puede ser null, en el caso de que sea null
     * no se ejecuta el método.
     */
    val otroTipoString: String? = null
    println(otroTipoString?.length)

    //Uso del operador elvis para asignar valor por defecto en el caso de ser null
    val edad3 = edad2 ?:0
    println(edad3)

    //Operador de aserción no nulo y lanza excepción si no lo tiene (!!). El tipo de excepción que lanza es NPE
    println(otroTipoString!!.length)

}