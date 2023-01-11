package `02-ProgramacionOrientadaObjetos`

/*
 *
 */

class Animal constructor(var name: String="", var edad: Int=0){
    init {
        print("Se ha creado un animal")
    }
}

class Animal2(var name: String="", var edad: Int=0) { //

}// }

class Animal3(var name: String="") {
    val camada: MutableList<Animal3> = mutableListOf()
   // constructor(name: String, padre:Animal3)
}