package repositories.owner

import db.MongoDbManager
import models.Employee
import models.Owner
import org.litote.kmongo.deleteOneById
import org.litote.kmongo.findOneById
import org.litote.kmongo.getCollection
import org.litote.kmongo.save

class OwnerRepository: IOwnerRepository {
    override fun findAll(): List<Owner> {
        return MongoDbManager.database.getCollection<Owner>().find().toList()
    }

    override fun findById(id: String): Owner? {
        return MongoDbManager.database.getCollection<Owner>().findOneById(id)
    }

    override fun save(entity: Owner): Owner {
        MongoDbManager.database.getCollection<Owner>().save(entity)
        return entity
    }

    override fun delete(entity: Owner): Boolean {
        return MongoDbManager.database.getCollection<Owner>().deleteOneById(entity.id).wasAcknowledged()
    }
}