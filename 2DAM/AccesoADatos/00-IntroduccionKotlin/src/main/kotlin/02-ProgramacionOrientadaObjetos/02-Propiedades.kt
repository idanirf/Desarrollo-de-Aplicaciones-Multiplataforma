package `02-ProgramacionOrientadaObjetos`

/*
 * Var tenemos getter and setter
 *
 */
/*
 * Backing fields para personalizar el getter and setter
 * fields para referirnos al campo actual
 * Propiedades calculadas
 * Backing properties tener algo interno que sea mutable pero desde fuera sea inmutable
 * En java también existen listas mutables y no mutables, ser mutable no significa no ser modificable internamente
 * //En java public List <String> list = List.of() //Posibilidad de utilizar Guava como complemento
 * Propiedades con tipo no nulo debe inicializarse en el constructor.
 * lateinit inicialización tardía, hago antes sette antes que el getter
 */

class Persona(var nombre: String, var apellido: String) {
    var nombreCompleto: String = "$nombre $apellido"
        get() = field.toUpperCase()
        set(value) {
            field = value
        }
}