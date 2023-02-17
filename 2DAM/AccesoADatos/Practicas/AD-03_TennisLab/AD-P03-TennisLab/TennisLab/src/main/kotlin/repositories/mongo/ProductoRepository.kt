package repositories.mongo

import exceptions.PedidoException
import exceptions.ProductoException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import models.Pedido
import models.Producto
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import org.litote.kmongo.coroutine.toList
import services.mongo.MongoDbManager
@Single
@Named("ProductoRepository")
class ProductoRepository: IProductoRepository {
    val pedidoRepository = PedidoRepository()

    /**
     * Find all: Método que sirve para obtener todos los productos.
     *
     * @return Devuelve todos los productos.
     */
    override  fun findAll(): Flow<Producto> {
        val list = MongoDbManager.database.getCollection<Producto>().find().publisher.asFlow()
        try {
            return list
        }catch (e: Exception){
            throw ProductoException("Ha ocurrido un error al obtener los productos")
        }
    }

    /**
     * Find by id: Método que sirve para encontrar un producto por su id.
     *
     * @param id
     * @return Devuelve un producto por su id.
     */
    override suspend fun findById(id: String): Producto? {
        val enti =MongoDbManager.database.getCollection<Producto>().findOneById(id)
        try {
            return enti

        }catch (e: Exception){
            throw ProductoException("Ha ocurrido un error al obtener el producto con id: $id")
        }
    }

    /**
     * Save: Método para insertar un producto.
     *
     * @param entity
     */
    override suspend fun save(entity: Producto) {
        val pedido = pedidoRepository.findById(entity.pedido_id)
        if (pedido != null) {
            try {
                val enti = MongoDbManager.database.getCollection<Producto>().save(entity)
            } catch (e: Exception) {
                throw ProductoException("Ha ocurrido un error al insertar el producto $entity")
            }
        }else{
            println("No se ha podido insertar producto, no se encuentra el pedido con ese id")
        }
    }

    /**
     * Delete: Método para borrar un producto
     *
     * @param _id
     */
    override suspend fun delete(_id: String) {
        try {
            val enti = MongoDbManager.database.getCollection<Producto>().deleteOneById(_id)
        }catch (e: Exception){
            throw ProductoException("Ha ocurrido un error al borrar el producto")
        }
    }

    /**
     * Update: Método para actualizar un producto.
     *
     * @param entity
     */
    override suspend fun update(entity: Producto) {
        try {
            val enti = MongoDbManager.database.getCollection<Producto>().updateOneById(entity._id, entity)
        } catch (e: Exception) {
            throw ProductoException("Ha ocurrido un error al actualizar el producto $entity")
        }
    }
}