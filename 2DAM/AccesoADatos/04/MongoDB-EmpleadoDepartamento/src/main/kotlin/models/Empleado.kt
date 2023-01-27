package models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id
import org.litote.kmongo.newId
import java.time.LocalDate

@Serializable
data class Empleado(
    @BsonId @Contextual
    val id: Id<Empleado> = newId<Empleado>(),
    var nombre: String,
    var apellidos: String,
    @Contextual
    var fechaAlta: LocalDate,
    var departamento: Departamento? = null
) {
    override fun toString(): String {
        return "Empleado(id=$id, nombre='$nombre', apellidos='$apellidos', fechaAlta=$fechaAlta, departamento=$departamento)"
    }
}