package es.drodriguez.com.tennislabspring.repositories

import es.drodriguez.com.tennislabspring.models.Pedido
import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface PedidoRepository: CoroutineCrudRepository<Pedido, ObjectId> {
}