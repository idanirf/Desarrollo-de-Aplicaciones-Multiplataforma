package models

import java.time.LocalDate
import java.util.*

data class Vehiculo(
    val id: Long = 0,
    val uuid: String = UUID.randomUUID().toString(),
    var marca: String,
    var modelo: String,
    val matricula: String,
    val fechaMatriculacion: LocalDate,
    val tipoMotor: Motor,
    val createdAt: LocalDate,
    val updatedAt: LocalDate,
    val deleted: Boolean
) {
}

enum class Motor {
    DIESEL, GASOLINA, HIBRIDO, ELECTRICO

}
