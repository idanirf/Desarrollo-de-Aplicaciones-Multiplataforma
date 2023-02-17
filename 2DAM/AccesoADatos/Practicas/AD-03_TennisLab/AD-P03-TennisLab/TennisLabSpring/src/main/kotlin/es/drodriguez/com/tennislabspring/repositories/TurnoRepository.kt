package es.drodriguez.com.tennislabspring.repositories

import es.drodriguez.com.tennislabspring.models.Turno
import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TurnoRepository: CoroutineCrudRepository<Turno, ObjectId>{
}