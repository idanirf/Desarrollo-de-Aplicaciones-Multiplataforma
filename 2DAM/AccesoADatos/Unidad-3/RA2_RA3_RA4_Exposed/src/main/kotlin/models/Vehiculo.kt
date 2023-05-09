package models

import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

data class Vehiculo(
    val uuid: UUID = UUID.randomUUID(),
    var marca: String? = null,
    var modelo: String? = null,
    var matricula: String? = null,
    var persona: Long? = null
) {

    override fun toString(): String {
        return "Vehiculo(uuid=$uuid, marca=$marca, modelo=$modelo, matricula=$matricula, persona=$persona)"
    }
}