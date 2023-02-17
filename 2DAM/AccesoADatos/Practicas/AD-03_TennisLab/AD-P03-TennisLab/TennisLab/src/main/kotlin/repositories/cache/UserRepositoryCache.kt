package repositories.cache

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import exceptions.UserCachedException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import models.User
import mapper.databaseToModel
import mu.KotlinLogging
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import repositories.mongo.UserRepository
import services.cache.CacheClient


private val logger = KotlinLogging.logger {}
private const val REFRESH_TIME = 6 * 10000L
@Single
@Named("UserRepositoryCache")
class UserRepositoryCache(@Named("CacheClient") client: CacheClient ) : IUserRepositoryCached {
    private val remote = UserRepository()
    private val cached = client.queries



    suspend fun refresh() = withContext(Dispatchers.IO) {
        launch {
            do {
                cached.removeAllUsers()
                val listUsuarios = remote.findAll()
                listUsuarios.collect {
                    cached.insertUser(it._id,it.id,it.name,it.username,it.email,it.password,it.tipoUser.toString(),it.phone,it.website)
                }
                delay(REFRESH_TIME)
            } while (true)

        }
    }

    override fun findAll(): Flow<User> {
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

    override suspend fun findById(id: String): User? {
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
            cached.insertUser(entity._id,entity.id,entity.name,entity.username,entity.email,entity.password,entity.tipoUser.toString(),entity.phone,entity.website)
        }catch (e: UserCachedException){
            throw UserCachedException("Error al insertar usuario en la cache")
        }

    }

    override suspend fun update(entity: User) {
        try {
            cached.updateUser(entity.id,entity.name,entity.username,entity.email,entity.password,entity.tipoUser.toString(),entity.phone,entity.website,entity._id)
        }catch (e: UserCachedException){
            throw UserCachedException("Error al actualizar usuario en la cache")
        }
    }


    override suspend fun delete(id:String) {
        try {
            cached.deleteUser(id)
        }catch (e: UserCachedException){
            throw UserCachedException("Error al borrar usuario de la cache")
        }
    }
}