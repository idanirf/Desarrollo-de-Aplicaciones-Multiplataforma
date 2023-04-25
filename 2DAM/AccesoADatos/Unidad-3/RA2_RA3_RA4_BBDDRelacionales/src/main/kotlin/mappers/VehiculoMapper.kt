package mappers

import models.Motor
import models.Vehiculo
import models.VehiculoDto
import java.time.LocalDate

fun Vehiculo.VehiculoToVehiculoDto(): VehiculoDto {
    return VehiculoDto(
        id = id,
        uuid = uuid,
        marca = marca,
        modelo = modelo,
        matricula = matricula,
        fechaMatriculacion = fechaMatriculacion.toString(),
        tipoMotor = tipoMotor.toString(),
        createdAt = createdAt.toString(),
        updatedAt = updatedAt.toString(),
        deleted = deleted.toString())
}

fun VehiculoDto.VehiculoDtoToVehiculo(): Vehiculo {
    println(fechaMatriculacion)
    return Vehiculo(
        id = id,
        uuid = uuid,
        marca = marca,
        modelo = modelo,
        matricula = matricula,
        fechaMatriculacion = LocalDate.of(
            fechaMatriculacion.trim().split("-")[0].toInt(),
            fechaMatriculacion.trim().split("-")[1].toInt(),
            fechaMatriculacion.trim().split("-")[2].toInt()),

        tipoMotor = Motor.valueOf(tipoMotor),
        createdAt = LocalDate.of(
            createdAt.trim().split("-")[0].toInt(),
            createdAt.trim().split("-")[1].toInt(),
            createdAt.trim().split("-")[2].toInt()),
        updatedAt = LocalDate.of(
            updatedAt.trim().split("-")[0].toInt(),
            updatedAt.trim().split("-")[1].toInt(),
            updatedAt.trim().split("-")[2].toInt()
        ),
        deleted = deleted.toBoolean())
}