package repositories.mongo

import models.Pedido

/**
 * Interfaz del pedido repository
 */
interface IPedidoRepository : CrudRepository<Pedido,String> {
}