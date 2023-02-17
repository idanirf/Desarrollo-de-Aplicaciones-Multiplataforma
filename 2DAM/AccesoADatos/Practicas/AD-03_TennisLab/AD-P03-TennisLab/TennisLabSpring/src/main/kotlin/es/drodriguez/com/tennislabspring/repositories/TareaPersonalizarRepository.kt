package es.drodriguez.com.tennislabspring.repositories

import es.drodriguez.com.tennislabspring.models.TareaPersonalizacion
import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface TareaPersonalizarRepository: CoroutineCrudRepository<TareaPersonalizacion, ObjectId> {
}