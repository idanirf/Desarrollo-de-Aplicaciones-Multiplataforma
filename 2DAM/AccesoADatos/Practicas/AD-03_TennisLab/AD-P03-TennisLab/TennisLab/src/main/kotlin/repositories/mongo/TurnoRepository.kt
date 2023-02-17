package repositories.mongo

import exceptions.TurnoException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import models.Turno
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import org.litote.kmongo.coroutine.toList
import services.mongo.MongoDbManager
@Single
@Named("TurnoRepository")
class TurnoRepository : ITurnoRepository {

    val userRepository = UserRepository()

    /**
     * Find all: Método para obtener todos los turnos.
     *
     * @return Devuelve todos los turnos.
     */
    override fun findAll(): Flow<Turno> {
        val list = MongoDbManager.database.getCollection<Turno>().find().publisher.asFlow()
        try {
            return list
        }catch (e: Exception){
            throw TurnoException("Ha ocurrido un error al obetener los turnos")
        }
    }

    /**
     * Find by id: Método para obtener un turno por su id.
     *
     * @param id
     * @return Devuelve un turno
     */
    override suspend fun findById(id: String) : Turno? {
       // val enti =MongoDbManager.database.getCollection<Turno>().findOneById(id)
        val list = findAll().toList()
        val enti = list.filter{ it._id == id }.firstOrNull()
        try {
            return enti
        }catch (e: Exception){
            throw TurnoException("Ha ocurrido un error al obtener el turno con id: $id")
        }
    }

    /**
     * Save: Método para insertar un turnos
     *
     * @param entity
     */
    override suspend fun save(entity: Turno) {
        val user = userRepository.findById(entity.usuario_id)
        if (user != null) {
            try {
                val enti = MongoDbManager.database.getCollection<Turno>().save(entity)
            } catch (e: Exception) {
                throw TurnoException("Ha ocurrido un error al insertar un turno $entity")
            }
        }else{
            println("No se ha podido insertar turno, no se encuentra el usuario con ese id")
        }
    }

    /**
     * Delete: Método para borrar un turno.
     *
     * @param _id
     */
    override suspend fun delete(_id: String) {
        try {
            val enti = MongoDbManager.database.getCollection<Turno>().deleteOneById(_id)
        }catch (e: Exception){
            throw TurnoException("Ha ocurrido un error al borrar el turno")
        }
    }

    /**
     * Update: Método para actualizar un turno.
     *
     * @param entity
     */
    override suspend fun update(entity: Turno) {
        try {
            val enti = MongoDbManager.database.getCollection<Turno>().updateOneById(entity._id, entity)
        } catch (e: Exception) {
            throw TurnoException("Ha ocurrido un error al actualizar el turno $entity")
        }
    }
}