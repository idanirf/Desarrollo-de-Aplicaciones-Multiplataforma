package repositories

import models.Vehiculo
import database.VehiculoDto
import java.util.*

interface ICrudRepositoryVehiculo: ICrudRepository<Long, database.VehiculoDto> {

}