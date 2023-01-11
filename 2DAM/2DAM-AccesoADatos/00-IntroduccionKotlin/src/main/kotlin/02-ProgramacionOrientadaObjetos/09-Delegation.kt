package `02-ProgramacionOrientadaObjetos`

import kotlin.properties.Delegates


/*
 * Dejamos a otra entidad que se encargue de manejar nuestras propiedades
 * Lazy se instancia cuando sea necesario. Cuando haya un by es que está delegando
 * Diferencia entre lateinnit no asignas valor solo para los var y los lazy asignas el valor solo para los val.
 * Observable cuando detecta que ha cambiado una variable en ese momento actua, programación reactiva
 * Vetoable -> cuando se actualiza una propiedad se ejecuta una accion, solo si se cumple cierta condición
 */

class Delegate {
    val lazyValue: String by lazy {
        println("Ahora me creo soy Lazy y me generó cuando me llaman")
        "Hola"
    }

    /*
     * ¿Qué es el patrón Observer?
     *  Observer es un patrón de diseño de comportamiento que te permite definir un mecanismo de suscripción para
     *  notificar a varios objetos sobre cualquier evento que le suceda al objeto que están observando
     */
    var observableValue: String by Delegates.observable("Hola"){_, old, new ->
        println("La variable $old y $new")
    }

    var veteoableValue: Int by Delegates.vetoable(0) {_, old, new ->
        println("La variable $old y $new")
        new in 1 .. 10
    }
}