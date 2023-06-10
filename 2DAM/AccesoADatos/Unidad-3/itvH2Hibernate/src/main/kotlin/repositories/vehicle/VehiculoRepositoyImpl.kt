package repositories.vehicle

import controllers.HibernateManager
import controllers.HibernateManager.manager
import models.Vehicle

class VehiculoRepositoyImpl: IVehicleRepository {
    override fun create(t: Vehicle): Vehicle {
        var vehicle: Vehicle = t
        HibernateManager.transaction {
            manager.persist(t)
            vehicle = manager.find(Vehicle::class.java, t.uuidVehicle)
        }
        return vehicle
    }

    override fun update(t: Vehicle): Boolean {
        var res = false
        HibernateManager.transaction {
            manager.merge(t)
            res = true
        }
        return res
    }

    override fun delete(t: Vehicle): Boolean {
        var res = false
        HibernateManager.transaction {
            val vehicle = manager.find(Vehicle::class.java, t.uuidVehicle)
            vehicle.let {
                manager.remove(it)
                res = true
            }
        }
        return res
    }

    override fun getById(id: String?): Vehicle? {
        var vehicle: Vehicle? = null
        HibernateManager.transaction {
            vehicle = manager.find(Vehicle::class.java, id)
        }
        return vehicle
    }

    override fun getAll(): List<Vehicle> {
        var vehicles = mutableListOf<Vehicle>()
        HibernateManager.query {
            val query = manager.createNamedQuery("Vehicle.findAll",Vehicle::class.java)
            vehicles = query.resultList
        }
        return vehicles
    }
}