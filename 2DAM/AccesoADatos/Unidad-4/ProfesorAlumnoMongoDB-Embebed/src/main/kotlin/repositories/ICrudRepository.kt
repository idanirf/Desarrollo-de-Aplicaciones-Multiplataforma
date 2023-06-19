package repositories

interface ICrudRepository<T, ID> {
    fun findAll(): List<T>
    fun findById(id: ID): T?
    fun save(entity: T): T
    fun delete(entity: T): Boolean
}