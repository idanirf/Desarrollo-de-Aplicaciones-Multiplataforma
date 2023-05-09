package models

import java.util.*


data class Persona(
    var id: Long = 0,
    var nombre: String,
    var fechaCarntet: String,
){
    override fun toString(): String {
        return "Persona(id=$id, nombre='$nombre', fechaCarntet='$fechaCarntet')"
    }
}