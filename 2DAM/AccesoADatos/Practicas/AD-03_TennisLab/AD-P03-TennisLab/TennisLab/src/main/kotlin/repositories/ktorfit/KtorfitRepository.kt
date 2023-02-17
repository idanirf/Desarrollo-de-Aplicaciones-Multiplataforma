package repositories.ktorfit

import dto.TareaDto
import dto.UserDto
import exceptions.RestException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.withContext
import mu.KotlinLogging
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import services.rest.KtorFitClient

private val logger = KotlinLogging.logger {}
@Single
@Named("KtorfitRepository")
class KtorfitRepository : IKtorfitRepository {

    private val client by lazy { KtorFitClient.instance }


     suspend fun findAll(): Flow<UserDto> = withContext(Dispatchers.IO) {
        val call = client.getAll(0,100)
        try {
            logger.debug { "findAll(page= 0, perPage= 100) - OK \n" }
            return@withContext call.asFlow()
        }catch (e: Exception){
            logger.debug { "findAll(page= 0, perPage= 100) - ERRROR \n" }
            throw RestException("Error al obtener los usuarios")
        }

    }
     override suspend fun findAllTareas(): Flow<TareaDto> = withContext(Dispatchers.IO) {
        val call = client.getAllTareas(0,100)
        try {
            logger.debug { "findAllTarea(page= 0, perPage= 100) - OK \n" }
            return@withContext call.asFlow()
        }catch (e: Exception){
            logger.debug { "findAllTarea(page= 0, perPage= 100) - ERRROR \n" }
            throw RestException("Error al obtener las tareas")
        }

    }




    override suspend fun create(entity: TareaDto ): TareaDto {
        val call = client.create(entity)
        try {
            logger.debug("create($entity) - OK")
            return call
        }catch (e: Exception){
            logger.debug("create($entity) - ERROR")
            throw RestException("Error al crear la tarea")
        }


    }

    override suspend fun update(entity: TareaDto): TareaDto {
        val call = client.update(entity.id, entity)
        try {
            logger.debug("update($entity) - OK")
            return call
        }catch (e: Exception){
            logger.debug("update($entity) - ERROR")
            throw RestException("Error al actualizar la tarea")
        }
    }

    override suspend fun delete(id:String ): TareaDto {
        val call = client.delete(id.toString())
        try {
            logger.debug("delete($id) - OK")
            return call
        } catch (e: Exception) {
            logger.debug("delete($id) - ERROR")
            throw RestException("Error al borrar la tarea")
        }
    }
}