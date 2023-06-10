package repositories.owner

import controllers.HibernateManager
import controllers.HibernateManager.manager
import models.Owner

class OwnerRepositoryImpl: IOwnerRepository {
    override fun create(t: Owner): Owner {
        var owner: Owner = t
        HibernateManager.transaction {
            manager.persist(t)
            owner = manager.find(Owner::class.java, t.uuidOwner)
        }
        return owner
    }

    override fun update(t: Owner): Boolean {
        var res = false
        HibernateManager.transaction {
            manager.merge(t)
            res = true
        }
        return res
    }

    override fun delete(t: Owner): Boolean {
        var res = false
        HibernateManager.transaction {
            val owner = manager.find(Owner::class.java, t.uuidOwner)
            owner.let {
                manager.remove(it)
                res = true
            }
        }
        return res
    }

    override fun getById(id: String?): Owner? {
        var res: Owner? = null
        HibernateManager.transaction {
            res = manager.find(Owner::class.java, id)
        }
        return res
    }

    override fun getAll(): List<Owner> {
        var owner = mutableListOf<Owner>()
        HibernateManager.transaction {
            val query = manager.createNamedQuery("Owner.findAll",Owner::class.java)
            owner = query.resultList
        }
        return owner
    }
}