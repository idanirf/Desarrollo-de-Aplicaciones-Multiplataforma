package repositories.mongo

import exceptions.PedidoException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import models.Pedido
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import org.litote.kmongo.coroutine.toList
import services.mongo.MongoDbManager
@Single
@Named("PedidoRepository")
class PedidoRepository : IPedidoRepository {
    private val userRepository = UserRepository()

    /**
     * Find all: Método para obtener todos los pedidos.
     *
     * @return Devuelve todos los pedidos.
     */
    override fun findAll(): Flow<Pedido> {
        val list = MongoDbManager.database.getCollection<Pedido>().find().publisher.asFlow()
        try {
            return list
        }catch (e: Exception){
            throw PedidoException("Ha ocurrido un error al obetener los pedidos")
        }
    }

    /**
     * Find by id: Método para obtener el pedido por su id.
     *
     * @param id
     * @return Devuelve un pedido.
     */
    override suspend fun findById(id: String): Pedido? {
        val enti =MongoDbManager.database.getCollection<Pedido>().findOneById(id)
        try {
            return enti

        }catch (e: Exception){
            throw PedidoException("Ha ocurrido un error al obtener el pedido con id: $id")
        }
    }

    /**
     * Save: Método para insertar un pedido.
     *
     * @param entity
     */
    override suspend fun save(entity: Pedido) {
        val user = userRepository.findById(entity.usuario_id)
        if (user != null) {
            try {
                val enti = MongoDbManager.database.getCollection<Pedido>().save(entity)
            } catch (e: Exception) {
                throw PedidoException("Ha ocurrido un error al insertar el pedido $entity")
            }
        }else{
            println("No se ha podido insertar turno, no se encuentra el usuario con ese id")
        }
    }

    override suspend fun delete(_id: String) {
        try {
            val enti = MongoDbManager.database.getCollection<Pedido>().deleteOneById(_id)
        }catch (e: Exception){
            throw PedidoException("Ha ocurrido un error al borrar el pedido")
        }
    }

    override suspend fun update(entity: Pedido) {
        try {
            val enti = MongoDbManager.database.getCollection<Pedido>().updateOneById(entity._id, entity)
        } catch (e: Exception) {
            throw PedidoException("Ha ocurrido un error al actualizar el pedido $entity")
        }
    }
}