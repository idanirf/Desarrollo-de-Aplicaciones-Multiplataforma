package `02-ProgramacionOrientadaObjetos`

/*
 * Metodos que debe implementar una clase
 * Implementación por defecto y propiedades en una interfaz.
 *
 */

interface  InterfazA{
    fun metodoA(): String = "Interfaz A método A"
    fun metodoB(): String = "Interfaz A método B"
}

interface InterfazB{
    fun metodoA(): String = "Interfaz B método A"
    fun metodoB(): String
}

interface InterfazC{
    val name: String
    fun a() = "Hola $name"
}


//todo porque este error??
/*interface Interfaz: InterfazA, InterfazB, InterfazC {
    //Ejecutamos el método de la interfaz C.
    override fun a() = super<InterfazB>.metodoA()

}*/


