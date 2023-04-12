package repositories

import models.Vehiculo
import java.util.*

class CrudRepositoryVehiculoImplement: ICrudRepositoryVehiculo {
    override fun findByUuidVehiculo(uuid: UUID): Vehiculo? {
        TODO("Not yet implemented")
    }

    override fun findByMarcaVehiculo(marca: String): List<Vehiculo> {
        TODO("Not yet implemented")
    }

    override fun findByMatricula(matricula: String): Vehiculo? {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Vehiculo> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Int): Vehiculo? {
        TODO("Not yet implemented")
    }

    override fun save(entity: Vehiculo): Vehiculo {
        TODO("Not yet implemented")
    }

    override fun update(entity: Vehiculo): Vehiculo {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}