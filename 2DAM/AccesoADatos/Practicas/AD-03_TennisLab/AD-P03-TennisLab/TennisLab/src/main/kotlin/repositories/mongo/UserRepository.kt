package repositories.mongo

import exceptions.UserException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import services.mongo.MongoDbManager
import models.User
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import org.litote.kmongo.coroutine.toList

//Hay que hacerlo con flujos para que sea reactivo
@Single
@Named("UserRepository")
class UserRepository : IUsersRepository {

    /**
     * Find all: Método para obtener todos los usuarios.
     *
     * @return Devuelve todos los usuarios.
     */
    override fun findAll(): Flow<User>{
        val list = MongoDbManager.database.getCollection<User>().find().publisher.asFlow()
        try {
            return list
        }catch (e: Exception){
            throw UserException("Ha ocurrido un error al obetener los usuarios")
        }

    }

    /**
     * Find all users: Metodo para obtener todos los usuarios.
     *
     * @return Devuelve
     */
    fun findAllUsers(): Flow<User>{
        val list = MongoDbManager.database.getCollection<User>().find().publisher
        try {
            return list.asFlow()
        }catch (e: Exception){
            throw UserException("Ha ocurrido un error al obetener los usuarios")
        }

    }

    /**
     * Find by id: Método para obtener un usuario por su id.
     *
     * @param id
     * @return Devuelve un usuario.
     */
    override suspend fun findById(id: String): User? {
        val enti =MongoDbManager.database.getCollection<User>().findOneById(id)
        try {
            return enti

        }catch (e: Exception){
            throw UserException("Ha ocurrido un error al obtener el usuario con id: $id")
        }
    }

    /**
     * Find by email: Método para obtener un usuario por su email.
     *
     * @param email
     * @return retorna el usuario con su email.
     */
    suspend fun findByEmail(email: String): User? {
        val list = findAll().toList()
        val enti = list.filter{ it.email == email }.firstOrNull()
        try {
            return enti

        }catch (e: Exception){
            throw UserException("Ha ocurrido un error al obtener el usuario con email: $email")
        }
    }


    /**
     * Save: Método para insertar un usuario.
     *
     * @param entity
     */
    override suspend fun save(entity: User) {
        try {
            val enti = MongoDbManager.database.getCollection<User>().save(entity)
        }catch (e: Exception){
            throw UserException("Ha ocurrido un error al insertar el usuario $entity")
        }
    }

    /**
     * Delete: Método para borrar un usuario.
     *
     * @param _id
     */
    override suspend fun delete(_id: String ) {
        try {
            val enti = MongoDbManager.database.getCollection<User>().deleteOneById(_id)
        }catch (e: Exception){
            throw UserException("Ha ocurrido un error al borrar el usuario ")
        }
    }

    /**
     * Update: Método para actualizar un usuario.
     *
     * @param entity
     */
    override suspend fun update(entity: User) {
        try {
            val enti = MongoDbManager.database.getCollection<User>().updateOneById(entity._id, entity)
        } catch (e: Exception) {
            throw UserException("Ha ocurrido un error al actualizar el usuario $entity")
        }
    }
}
