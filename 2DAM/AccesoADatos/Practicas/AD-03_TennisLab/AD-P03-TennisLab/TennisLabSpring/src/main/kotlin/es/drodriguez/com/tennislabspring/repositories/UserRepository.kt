package es.drodriguez.com.tennislabspring.repositories

import es.drodriguez.com.tennislabspring.models.User
import kotlinx.coroutines.flow.Flow
import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: CoroutineCrudRepository<User, ObjectId> {
}