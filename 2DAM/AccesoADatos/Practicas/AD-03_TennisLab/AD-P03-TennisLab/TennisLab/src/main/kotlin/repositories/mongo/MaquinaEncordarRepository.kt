package repositories.mongo

import exceptions.MaquinaEncordarException
import exceptions.TurnoException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import models.MaquinaEncordar
import models.TareaEncordado
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import org.litote.kmongo.coroutine.toList
import services.mongo.MongoDbManager

/**
 * Maquina encordar repository
 *
 */
@Single
@Named("MaquinaEncordarRepository")
class MaquinaEncordarRepository : IMaquinaEncordarRepository {
    private val turnoRepository = TurnoRepository()

    /**
     * Find all: Método que sirve para obtener las máquinas encordadas.
     *
     * @return Devuelve las máquinas encordadas
     */
    override  fun findAll(): Flow<MaquinaEncordar> {
        val list = MongoDbManager.database.getCollection<MaquinaEncordar>().find().publisher.asFlow()
        try{
            return list
        }catch (e: Exception){
            throw MaquinaEncordarException("Ha ocurrido un error al obtener las maquinas encordar")
        }
    }

    /**
     * Find by id: Método que sirve para encontrar la máquina encordada por su id.
     *
     * @param id
     * @return Devuelve la máquina encordada por su id.
     */
    override suspend fun findById(id: String): MaquinaEncordar? {
        val enti = MongoDbManager.database.getCollection<MaquinaEncordar>().findOneById(id)
        try {
            return enti
        }catch (e: Exception){
            throw MaquinaEncordarException("Ha ocurrido un error al obtener la maquina encordar con id: $id")
        }
    }

    /**
     * Save: Método para insertar una nueva máquina encordada.
     *
     * @param entity
     */
    override suspend fun save(entity: MaquinaEncordar) {
        val turn = turnoRepository.findById(entity.turno_id!!)
        if (turn != null) {
            try {
                val enti = MongoDbManager.database.getCollection<MaquinaEncordar>().save(entity)
            } catch (e: Exception) {
                throw TurnoException("Ha ocurrido un error al insertar una nueva maquina de encordado $entity")
            }
        }else{
            println("No se ha podido insertar una nueva maquina de encordado, no se encuentra el turno con ese id")
        }
    }

    /**
     * Delete: Métod para borrar una máquina encordada.
     *
     * @param _id
     */
    override suspend fun delete(_id: String) {
        try{
            val enti = MongoDbManager.database.getCollection<MaquinaEncordar>().deleteOneById(_id)
        }catch (e: Exception){
            throw MaquinaEncordarException("Ha ocurrido un error al borrar la maquina encordar")
        }
    }

    /**
     * Update: Método para actualizar una máquina encordada.
     *
     * @param entity
     */
    override suspend fun update(entity: MaquinaEncordar) {
        try{
            val enti = MongoDbManager.database.getCollection<MaquinaEncordar>().updateOneById(entity._id, entity)
        }catch (e: Exception){
            throw MaquinaEncordarException("Ha ocurrido un error al actualizar la maquina encordar $entity")
        }
    }
}