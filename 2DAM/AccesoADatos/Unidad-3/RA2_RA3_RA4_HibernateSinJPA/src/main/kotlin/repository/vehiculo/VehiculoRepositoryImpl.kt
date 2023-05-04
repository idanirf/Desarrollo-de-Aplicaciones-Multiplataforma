package repository.vehiculo

import Controller.HibernateManager
import Controller.HibernateManager.manager
import models.Vehiculo
import java.util.ArrayList

class VehiculoRepositoryImpl: IVehiculoRepository {
    override fun create(t: Vehiculo): Vehiculo {
        var vehiculo: Vehiculo = t
        HibernateManager.transaction {
            manager.persist(t)
            vehiculo = manager.find(Vehiculo::class.java, t.uuid)
        }
        return vehiculo
    }

    override fun update(t: Vehiculo): Boolean {
        var res = false
        HibernateManager.transaction {
            manager.merge(t)
            res = true
        }
        return res
    }

    override fun delete(t: Vehiculo): Boolean {
        var res = false
        HibernateManager.transaction {
           val vehiculo = manager.find(Vehiculo::class.java, t.uuid)
            vehiculo.let {
                manager.remove(it)
                res = true
            }
        }
        return res
    }

    override fun getById(id: Long): Vehiculo? {
        var res : Vehiculo? = null
        HibernateManager.transaction {
            res = manager.find(Vehiculo::class.java, id)
        }
        return res
    }

    override fun getAll(): List<Vehiculo> {
        var vehiculos = mutableListOf<Vehiculo>()
        HibernateManager.query {
            val query = manager.createNamedQuery("Vehiculo.findAll", Vehiculo::class.java)
            vehiculos = query.resultList
        }
        return vehiculos
    }
}