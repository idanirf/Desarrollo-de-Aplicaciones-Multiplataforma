package repositories.mongo

import kotlinx.coroutines.flow.Flow

interface CrudRepository<T,ID> {
     fun findAll(): Flow<T>
    suspend fun findById(id: ID): T?
    suspend fun save(entity: T)
    suspend fun delete(_id: String)
    suspend fun update(entity: T)
}