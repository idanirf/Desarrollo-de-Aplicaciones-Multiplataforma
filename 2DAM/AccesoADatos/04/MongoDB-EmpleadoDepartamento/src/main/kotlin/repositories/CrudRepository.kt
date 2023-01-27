package repositories

import kotlinx.coroutines.flow.Flow
import models.Empleado

interface CrudRepository<T, ID> {
    fun findAll(): Flow<T>
    suspend fun findById(id: ID): T?
    suspend fun save(entity: T): Empleado?
    suspend fun delete(entity: T): Boolean
}