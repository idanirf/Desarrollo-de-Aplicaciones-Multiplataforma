package es.drodriguez.com.tennislabspring.repositories

import es.drodriguez.com.tennislabspring.models.Producto
import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductoRepository: CoroutineCrudRepository<Producto, ObjectId>{
}