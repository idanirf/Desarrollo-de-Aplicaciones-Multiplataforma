package models

import java.util.UUID

class Vehiculo(
    var uuid: String = UUID.randomUUID().toString(),
    var marca: String = "",
    var modelo: String = "",
    var matricula: String = "",
    var uuidPersona: String? = null
) {
    override fun toString(): String {
        return "Vehiculo(uuid='$uuid', marca='$marca', modelo='$modelo', matricula='$matricula', uuidPersona=$uuidPersona)"
    }
}