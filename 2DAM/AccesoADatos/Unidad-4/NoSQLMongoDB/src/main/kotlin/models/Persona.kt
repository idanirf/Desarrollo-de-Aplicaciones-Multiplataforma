package models

import java.util.UUID

class Persona(
    var uuid: String = UUID.randomUUID().toString(),
    var nombre: String,
    var email: String
) {
    override fun toString(): String {
        return "Persona(uuid='$uuid', nombre='$nombre', email='$email')"
    }
}