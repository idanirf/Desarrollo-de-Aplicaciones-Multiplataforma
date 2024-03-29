package repositories.vehicle

import models.Vehicle
import repositories.ICrudRepository

interface IVehicleRepository: ICrudRepository<Long, Vehicle> {
}