package repositories

import models.Vehiculo
import java.util.*

interface ICrudRepositoryVehiculo: ICrudRepository<Vehiculo, Int> {
    fun findByUuidVehiculo(uuid: UUID): Vehiculo?
    fun findByMarcaVehiculo(marca: String): List<Vehiculo>
    fun findByMatricula(matricula: String): Vehiculo?
}