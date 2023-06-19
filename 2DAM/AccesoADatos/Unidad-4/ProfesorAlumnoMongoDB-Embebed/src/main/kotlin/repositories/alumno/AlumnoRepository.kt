package repositories.alumno

import db.MongoManager
import models.Alumno
import org.litote.kmongo.deleteOneById
import org.litote.kmongo.findOneById
import org.litote.kmongo.getCollection

class AlumnoRepository: IAlumnoRepository {
    override fun findAll(): List<Alumno> {
        return MongoManager.db.getCollection<Alumno>("alumnos").find().toList()
    }

    override fun findById(id: String): Alumno? {
        return MongoManager.db.getCollection<Alumno>("alumnos").findOneById(id)
    }

    override fun save(entity: Alumno): Alumno {
        MongoManager.db.getCollection<Alumno>("alumnos").insertOne(entity)
        return entity
    }

    override fun delete(entity: Alumno): Boolean {
        return MongoManager.db.getCollection<Alumno>("alumnos").deleteOneById(entity.id).wasAcknowledged()
    }
}