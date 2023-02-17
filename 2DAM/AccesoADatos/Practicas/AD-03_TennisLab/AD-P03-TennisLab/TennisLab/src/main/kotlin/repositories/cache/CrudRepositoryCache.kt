package repositories.cache

import kotlinx.coroutines.flow.Flow


interface CrudRepositoryCache<T,ID> {

    fun findAll(): Flow<T>
    suspend fun findById(id: ID): T?
    //suspend fun save(entity: T)
    suspend fun update(entity: T)
    suspend fun delete(id: ID)
}