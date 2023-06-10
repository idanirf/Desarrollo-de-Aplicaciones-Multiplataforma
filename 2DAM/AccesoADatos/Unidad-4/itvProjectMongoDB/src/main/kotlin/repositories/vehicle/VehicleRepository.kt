package repositories.vehicle

import db.MongoDbManager
import models.Vehicle
import org.litote.kmongo.deleteOneById
import org.litote.kmongo.findOneById
import org.litote.kmongo.getCollection
import org.litote.kmongo.save

class VehicleRepository: IVehicleRepository {
    override fun findAll(): List<Vehicle> {
        return MongoDbManager.database.getCollection<Vehicle>().find().toList()
    }

    override fun findById(id: String): Vehicle? {
        return MongoDbManager.database.getCollection<Vehicle>().findOneById(id)
    }

    override fun save(entity: Vehicle): Vehicle {
       MongoDbManager.database.getCollection<Vehicle>().save(entity)
        return entity
    }

    override fun delete(entity: Vehicle): Boolean {
        return MongoDbManager.database.getCollection<Vehicle>().deleteOneById(entity.id).wasAcknowledged()
    }
}