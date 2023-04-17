package repositories

interface ICrudRepository<T, ID> {
    fun create(entity: T): T
    fun update(entity: T): T
    fun delete(entity: T)
    fun findById(id: Int): T
    fun findAll(): List<T>
}