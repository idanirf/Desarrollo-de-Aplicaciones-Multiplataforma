package mappers

import models.Persona
import org.bson.Document

fun Persona.toDocument(): Document? {
    val dc = Document()
    dc.append("uuid", this.uuid)
    dc.append("nombre", this.nombre)
    dc.append("email", this.email)
    return dc
}

fun Document.toPersona(): Persona? {
    val uuid = this.get("uuid")?.toString()
    val nombre = this.get("nombre")?.toString()
    val email = this.get("email")?.toString()
    if (uuid== null || nombre== null || email== null) {
        return null
    }
    return Persona(uuid, nombre, email)
}
