package models

import java.time.LocalDate
import java.util.*

data class Empleado(
    val id: UUID = UUID.randomUUID(),
    val nombre: String,
    val fechaNacimiento: LocalDate,
    val departamento: Departamento

) {
    override fun toString(): String {
        return "Empleado(id=$id, nombre='$nombre', fechaNacimiento=$fechaNacimiento)"
    }
}
