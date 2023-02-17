package repositories.mongo

import exceptions.MaquinaPersonalizarException
import exceptions.TurnoException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import models.MaquinaEncordar
import models.MaquinaPersonalizar
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import org.litote.kmongo.coroutine.toList
import services.mongo.MongoDbManager
@Single
@Named("MaquinaPersonalizarRepository")
class MaquinaPersonalizarRepository : IMaquinaPersonalizarRepository {
    private val turno = TurnoRepository()

    /**
     * Find all: Método para encontrar todas las máquinas de personalizar.
     *
     * @return Devuelve la máquina de personalizar.
     */
    override fun findAll(): Flow<MaquinaPersonalizar> {
        val list = MongoDbManager.database.getCollection<MaquinaPersonalizar>().find().publisher.asFlow()
        try {
            return list
        }catch (e: Exception){
            throw MaquinaPersonalizarException("Ha ocurrido un error al obtener las maquinas")
        }
    }

    /**
     * Find by id: Método para encontrar por su id la máquina de personalizar.
     *
     * @param id
     * @return Devuelve la máquina por su id.
     */
    override suspend fun findById(id: String): MaquinaPersonalizar? {
        val enti =MongoDbManager.database.getCollection<MaquinaPersonalizar>().findOneById(id)
        try {
            return enti
        }catch (e:Exception){
            throw MaquinaPersonalizarException("Ha ocurrido un error al obtener la maquina personalizar con id: $id")
        }
    }

    /**
     * Save: Método para insertar una nueva máquina de personalizar.
     *
     * @param entity
     */
    override suspend fun save(entity: MaquinaPersonalizar) {
        val turn = turno.findById(entity.turno_id!!)
        if (turn != null) {
            try {
                val enti = MongoDbManager.database.getCollection<MaquinaPersonalizar>().save(entity)
            } catch (e: Exception) {
                throw TurnoException("Ha ocurrido un error al insertar una nueva maquina de personalizar $entity")
            }
        }else{
            println("No se ha podido insertar una nueva maquina de personalizar, no se encuentra el turno con ese id")
        }
    }

    /**
     * Delete: Método para borrar una máquina de personalizar.
     *
     * @param _id
     */
    override suspend fun delete(_id: String) {
       try{
           val enti = MongoDbManager.database.getCollection<MaquinaPersonalizar>().deleteOneById(_id)
       }catch (e: Exception){
           throw MaquinaPersonalizarException("Ha ocurrido un error al borrar la maquina personalizar")
       }
    }

    /**
     * Update: Método para actualizar una máquina de personalizar.
     *
     * @param entity
     */
    override suspend fun update(entity: MaquinaPersonalizar) {
        try{
            val enti = MongoDbManager.database.getCollection<MaquinaPersonalizar>().updateOneById(entity._id, entity)
        }catch (e: Exception){
            throw MaquinaPersonalizarException("Ha ocurrido un error al actualizar la maquina personalizar $entity")
        }
    }
}