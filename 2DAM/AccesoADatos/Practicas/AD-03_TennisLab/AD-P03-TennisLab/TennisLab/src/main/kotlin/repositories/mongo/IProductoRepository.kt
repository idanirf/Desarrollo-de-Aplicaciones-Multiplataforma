package repositories.mongo

import models.Producto

/**
 * Interfaz del producto repository.
 */
interface IProductoRepository: CrudRepository<Producto,String>{
}