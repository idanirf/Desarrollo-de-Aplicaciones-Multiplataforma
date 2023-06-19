package repositories.asignatura

import db.MongoManager
import models.Asignatura
import org.litote.kmongo.deleteOneById
import org.litote.kmongo.findOneById
import org.litote.kmongo.getCollection
import org.litote.kmongo.save

class AsignaturaRepository: IAsignaturaRepository {
    override fun findAll(): List<Asignatura> {
        return MongoManager.db.getCollection<Asignatura>().find().toList()
    }

    override fun findById(id: String): Asignatura? {
        return MongoManager.db.getCollection<Asignatura>().findOneById(id)
    }

    override fun save(entity: Asignatura): Asignatura {
        MongoManager.db.getCollection<Asignatura>().save(entity)
        return entity
    }

    override fun delete(entity: Asignatura): Boolean {
        return MongoManager.db.getCollection<Asignatura>().deleteOneById(entity.id).wasAcknowledged()
    }
}