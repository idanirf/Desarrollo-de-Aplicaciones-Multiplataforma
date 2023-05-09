package mapper

import entities.VehiculoDao
import models.Vehiculo

fun VehiculoDao.toVehiculo(): Vehiculo {
    return Vehiculo(
        uuid = this.id.value,
        marca = this.marca,
        modelo = this.modelo,
        matricula = this.matricula,
        persona = this.persona,
    )
}

