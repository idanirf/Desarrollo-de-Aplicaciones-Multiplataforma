package storage

import models.VehiculoDto

interface Storage {

    fun readVehiculoDto(url : String): ArrayList<VehiculoDto>
    fun writeVehiculoDto(url: String, vehiculo: List<VehiculoDto>): Boolean
}