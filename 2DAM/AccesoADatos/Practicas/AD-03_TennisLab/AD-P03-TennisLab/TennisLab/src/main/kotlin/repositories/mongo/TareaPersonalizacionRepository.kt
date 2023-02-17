package repositories.mongo

import exceptions.PedidoException
import exceptions.TurnoException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import models.Pedido
import models.TareaEncordado
import models.TareaPersonalizacion
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import org.litote.kmongo.coroutine.toList
import services.mongo.MongoDbManager
@Single
@Named("TareaPersonalizacionRepository")
class TareaPersonalizacionRepository: ITareaPersonalizacion {
    val pedido = PedidoRepository()

    /**
     * Find all: Método para obtener todas las tareas de personalización
     *
     * @return Devuelve las tareas de personalización
     */
    override fun findAll(): Flow<TareaPersonalizacion> {
        val list = MongoDbManager.database.getCollection<TareaPersonalizacion>().find().publisher.asFlow()
        try {
            return list
        }catch (e: Exception){
            throw TurnoException("Ha ocurrido un error al obtener las tareas de personalización")
        }
    }

    /**
     * Find by id: Método para obtener una tarea de personalización
     *
     * @param id
     * @return Devuelve una tarea de personalización.
     */
    override suspend fun findById(id: String): TareaPersonalizacion? {
        val enti =MongoDbManager.database.getCollection<TareaPersonalizacion>().findOneById(id)
        try {
            return enti

        }catch (e: Exception){
            throw PedidoException("Ha ocurrido un error al obtener el id de la tarea personalización: $id")
        }
    }

    /**
     * Save: Insertar una tarea de personalización
     *
     * @param entity
     */
    override suspend fun save(entity: TareaPersonalizacion) {
        val ped = pedido.findById(entity.pedido_id!!)
        if (ped != null) {
            try {
                val enti = MongoDbManager.database.getCollection<TareaPersonalizacion>().save(entity)
            } catch (e: Exception) {
                throw TurnoException("Ha ocurrido un error al insertar una tarea de personalización $entity")
            }
    }
    else{
        println("No se ha podido insertar tarea de personalización, no se encuentra la tarea de personalización con el id")
    }
    }

    /**
     * Delete: Método para borrar una tarea de personalización
     *
     * @param _id
     */
    override suspend fun delete(_id: String) {
        try {
            val enti = MongoDbManager.database.getCollection<TareaPersonalizacion>().deleteOneById(_id)
        }catch (e: Exception){
            throw TurnoException("Ha ocurrido un error al borrar la tarea de personalización ")
        }
    }

    /**
     * Update: Método para actualizar una tarea de personalización.
     *
     * @param entity
     */
    override suspend fun update(entity: TareaPersonalizacion) {
        try {
            val enti = MongoDbManager.database.getCollection<TareaPersonalizacion>().updateOneById(entity._id, entity)
        }catch (e: Exception){
            throw TurnoException("Ha ocurrido un error al actualizar la tarea de personalización $entity")
        }
    }
}
