package models

import java.time.LocalDate
import java.util.*

data class Vehiculo(
    val id: Int,
    val uuid: UUID,
    val marca: String,
    val modelo: String,
    val matricula: String,
    val fechaMatriculacion: LocalDate,
    val tipoMotor: Motor
) {
}

enum class Motor {
    DIESEL, GASOLINA, HIBRIDO, ELECTRICO

}
