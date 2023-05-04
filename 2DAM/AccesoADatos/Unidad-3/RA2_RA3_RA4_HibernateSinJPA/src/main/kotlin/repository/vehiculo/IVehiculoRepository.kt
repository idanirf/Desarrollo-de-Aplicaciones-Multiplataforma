package repository.vehiculo

import models.Vehiculo
import repository.ICrudRepository

interface IVehiculoRepository: ICrudRepository<Long, Vehiculo> {
}