package es.drodriguez.com.tennislabspring.repositories

import dto.TareaDto
import dto.UserDto
import exceptions.RestException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Repository
import services.rest.KtorFitClient

@Repository
class RestRepository{
    private val client by lazy { KtorFitClient.instance }


    suspend fun findAll(): Flow<UserDto> = withContext(Dispatchers.IO) {
        val call = client.getAll(0,100)
        try {
            return@withContext call.asFlow()
        }catch (e: Exception){
            throw RestException("Error al obtener los usuarios")
        }

    }

    suspend fun findAllTareas(): Flow<TareaDto> = withContext(Dispatchers.IO) {
        val call = client.getAllTareas(0,100)
        try {
            return@withContext call.asFlow()
        }catch (e: Exception){
            throw RestException("Error al obtener las tareas")
        }

    }




     suspend fun create(entity: TareaDto): TareaDto {
        val call = client.create(entity)
        try {
            return call
        }catch (e: Exception){
            throw RestException("Error al crear la tarea")
        }


    }

    suspend fun update(entity: TareaDto): TareaDto {
        val call = client.update(entity.id, entity)
        try {
            return call
        }catch (e: Exception){
            throw RestException("Error al actualizar la tarea")
        }
    }

    suspend fun delete(id:String ): TareaDto {
        val call = client.delete(id.toString())
        try {
            return call
        } catch (e: Exception) {
            throw RestException("Error al borrar la tarea")
        }
    }
}