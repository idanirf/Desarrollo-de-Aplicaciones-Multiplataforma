package repositories

interface ICrudRepository<ID, T> {
    fun create(t: T):T
    fun update(t: T):Boolean
    fun delete(t: T):Boolean
    fun getById(id: String?):T?
    fun getAll():List<T>
}