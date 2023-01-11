package `02-ProgramacionOrientadaObjetos`

/*
 * En Kotlin las clases son cerradas.
 * Herencia simple
 * Clases abstractas y no instanciadas
 * Clases finales y no heredadas
 * El método padre se puede ejecutar con el super
 */

//Clase Padre
abstract class ClasePadreA(val dato: String){
    //Función pública, por lo tanto, se puede heredar
    open fun imprimirFuncion(){
        println(dato)
    }
}

//Clase hijo 1
open class ClaseHijo1(dato: String): ClasePadreA(dato) {
    //Sobre escribimos el método imprimirFuncion
    override fun imprimirFuncion() {
        println(dato)
    }

    open fun imprimirPadre(){
        println(dato)
        super.imprimirFuncion()
    }
}



