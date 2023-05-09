package repository

interface CrudRepository<T, ID> {
    fun findAll(): List<T>
    fun findById(id: ID): T?
    fun deleteById(id: ID): Boolean
    fun update(t: T): Boolean
    fun create(t: T): T?
}