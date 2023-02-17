package es.drodriguez.com.tennislabspring.repositories

import es.drodriguez.com.tennislabspring.models.MaquinaEncordar
import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface MaquinaEncordarRepository: CoroutineCrudRepository<MaquinaEncordar, ObjectId> {
}