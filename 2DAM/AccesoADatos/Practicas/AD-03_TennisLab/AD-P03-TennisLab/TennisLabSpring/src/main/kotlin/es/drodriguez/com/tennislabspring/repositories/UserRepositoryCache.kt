package es.drodriguez.com.tennislabspring.repositories

import es.drodriguez.com.tennislabspring.models.User
import es.drodriguez.com.tennislabspring.services.cache.CacheClient
import exceptions.UserCachedException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import mapper.databaseToModel
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

private const val REFRESH_TIME = 60 * 10000L

@Repository
class UserRepositoryCache
    @Autowired constructor (client : CacheClient, var remote : UserRepository) {
    private val cached = client.queries


    suspend fun refresh() = withContext(Dispatchers.IO) {

        launch {
            do {
                cached.removeAllUsers()
                val listUsuarios = remote.findAll()
                listUsuarios.collect {
                    cached.insertUser(it._id.toString(),it.id,it.name,it.username,it.email,it.password,it.tipoUser.toString(),it.phone,it.website)
                }
                delay(REFRESH_TIME)
            } while (true)

        }
    }

     suspend fun findAll(): Flow<User> {
        val listaUsuarios = mutableListOf<User>()
        try {
            val list =  cached.selectUsers().executeAsList()
            list.forEach {
                val a = databaseToModel(it)
                listaUsuarios.add(a)
            }

            return listaUsuarios.asFlow()
        }catch (e: UserCachedException){
            throw UserCachedException("Error al obtener todos los usuarios de la cache")
        }
    }

     suspend fun findById(id: String): User? {
        try {
            val user = cached.selectByIdUser(id).executeAsOneOrNull()
            if (user != null){
                val entity = databaseToModel(user)
                return entity
            }else{
                throw UserCachedException("No se encuentra el usuario con el id $id")
            }
        }catch (e: UserCachedException){
            throw UserCachedException("Error al obtener el usuario con id: $id")
        }
    }

    suspend fun save(entity: User) {
        try {
            cached.insertUser(entity._id.toString(),entity.id,entity.name,entity.username,entity.email,entity.password,entity.tipoUser.toString(),entity.phone,entity.website)
        }catch (e: UserCachedException){
            throw UserCachedException("Error al insertar usuario en la cache")
        }

    }

     suspend fun update(entity: User) {
        try {
            cached.updateUser(entity.id,entity.name,entity.username,entity.email,entity.password,entity.tipoUser.toString(),entity.phone,entity.website,entity._id.toString())
        }catch (e: UserCachedException){
            throw UserCachedException("Error al actualizar usuario en la cache")
        }
    }


     suspend fun delete(id: ObjectId) {
        try {
            cached.deleteUser(id.toString())
        }catch (e: UserCachedException){
            throw UserCachedException("Error al borrar usuario de la cache")
        }
    }

}