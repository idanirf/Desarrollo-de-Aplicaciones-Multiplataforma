package repositories.mongo

import exceptions.PedidoException
import exceptions.TurnoException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import models.Pedido
import models.TareaEncordado
import models.TareaPersonalizacion
import models.Turno
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import org.litote.kmongo.coroutine.toList
import services.mongo.MongoDbManager
@Single
@Named("TareaEncordadoRepository")
class TareaEncordadoRepository: ITareaEncordado {
    val pedido = PedidoRepository()

    /**
     * Find all: Método para encontrar todas las tareas de encordadas.
     *
     * @return Devuelve las tareas encordadas.
     */
    override  fun findAll(): Flow<TareaEncordado> {
        val list = MongoDbManager.database.getCollection<TareaEncordado>().find().publisher.asFlow()
        try {
            return list
        }catch (e: Exception){
            throw TurnoException("Ha ocurrido un error al obtener las tareas de encordado")
        }
    }

    /**
     * Find by id: Método para encontrar la tarea encordada.
     *
     * @param id
     * @return Devuelve la tarea encordada.
     */
    override suspend fun findById(id: String): TareaEncordado? {
        val enti =MongoDbManager.database.getCollection<TareaEncordado>().findOneById(id)
        try {
            return enti

        }catch (e: Exception){
            throw PedidoException("Ha ocurrido un error al obtener el id de la tarea de encordado: $id")
        }
    }

    /**
     * Save Método para insertar una tara encordada.
     *
     * @param entity
     */
    override suspend fun save(entity: TareaEncordado) {
        val ped = pedido.findById(entity.pedido_id!!)
        if (ped != null) {
            try {
                val enti = MongoDbManager.database.getCollection<TareaEncordado>().save(entity)
            } catch (e: Exception) {
                throw TurnoException("Ha ocurrido un error al insertar una tarea de encordado $entity")
            }
        }else{
            println("No se ha podido insertar tarea de encordado, no se encuentra el pedido con ese id")
        }
    }

    /**
     * Delete: Método para borrar una tarea encordada.
     *
     * @param _id
     */
    override suspend fun delete(_id: String) {
        try {
            val enti = MongoDbManager.database.getCollection<TareaEncordado>().deleteOneById(_id)
        }catch (e: Exception){
            throw TurnoException("Ha ocurrido un error al borrar la tarea de encordado")
        }
    }

    /**
     * Update: Método para actualizar la tarea encordada.
     *
     * @param entity
     */
    override suspend fun update(entity: TareaEncordado) {
        try {
            val enti = MongoDbManager.database.getCollection<TareaEncordado>().updateOneById(entity._id, entity)
        }catch (e: Exception){
            throw TurnoException("Ha ocurrido un error al actualizar la tarea de encordado $entity")
        }
    }
}