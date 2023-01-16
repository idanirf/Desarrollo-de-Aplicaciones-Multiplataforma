package models

import java.util.UUID

data class Departamento(
    val id: UUID = UUID.randomUUID(),
    val nombre: String,
    val presupuesto: Double,
) {
    override fun toString(): String {
        return "Departamento(id=$id, nombre='$nombre', presupuesto=$presupuesto)"
    }
}