package repositories.ktorfit

import kotlinx.coroutines.flow.Flow

interface CrudRepository<T,ID> {
    suspend fun findAllTareas(): Flow<T>

    suspend fun create(entity: T): T
    suspend fun update(entity: T): T
    suspend fun delete(id: ID) : T



}