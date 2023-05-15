package repository

interface ICrudRepostory<ID, T> {
    fun create(t: T)
    fun findById(uuid: ID): T?
    fun findAll(): List<T>
    fun delete(uuid: ID)
    fun deleteAll()
    fun update(t: T)
}