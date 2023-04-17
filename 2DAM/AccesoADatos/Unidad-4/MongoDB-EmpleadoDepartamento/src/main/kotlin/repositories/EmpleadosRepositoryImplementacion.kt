package repositories

import db.MongoDbManager
import exceptions.EmpleadoException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import models.Empleado
import org.litote.kmongo.Id

class EmpleadosRepositoryImplementacion: EmpleadosRepository {
    override fun findAll(): Flow<Empleado> {
        return MongoDbManager.database.getCollection<Empleado>()
            .find().publisher.asFlow()
    }

    override suspend fun findById(id: Id<Empleado>): Empleado? {
        return MongoDbManager.database.getCollection<Empleado>()
            .findOneById(id) ?: throw EmpleadoException("No existe el empleado con id $id")
    }

    override suspend fun save(entity: Empleado): Empleado? {
        return MongoDbManager.database.getCollection<Empleado>()
            .save(entity).let { entity }
    }

    override suspend fun delete(entity: Empleado): Boolean {
        return MongoDbManager.database.getCollection<Empleado>()
            .deleteOneById(entity.id).let { true }
    }
}