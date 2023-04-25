package models

import java.time.LocalDate
import java.util.*

data class Vehiculo(
    val id: Long = 0,
    val uuid: String = UUID.randomUUID().toString(),
    val marca: String,
    var modelo: String,
    val matricula: String,
    val fechaMatriculacion: LocalDate,
    val tipoMotor: Motor,
    val createdAt: LocalDate,
    val updatedAt: LocalDate,
    val deleted: Boolean
) {
    override fun toString(): String {
        return "Vehiculo(id=$id, uuid='$uuid', marca='$marca', modelo='$modelo', matricula='$matricula', fechaMatriculacion=$fechaMatriculacion, tipoMotor=$tipoMotor, createdAt=$createdAt, updatedAt=$updatedAt, deleted=$deleted)"
    }

}

enum class Motor {
    DIESEL, GASOLINA, HIBRIDO, ELECTRICO

}

