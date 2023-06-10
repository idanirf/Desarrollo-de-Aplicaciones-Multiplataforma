package es.drodriguez.itvspringmongodb.repositories

import es.drodriguez.itvspringmongodb.models.Owner
import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OwnerRepository: CoroutineCrudRepository<Owner, ObjectId> {
}