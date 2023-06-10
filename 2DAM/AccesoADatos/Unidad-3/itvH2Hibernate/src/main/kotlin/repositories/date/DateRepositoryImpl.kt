package repositories.date

import controllers.HibernateManager
import controllers.HibernateManager.manager
import models.Date

class DateRepositoryImpl : IDateRepository {
    override fun create(t: Date): Date {
        var date: Date = t
        HibernateManager.transaction {
            manager.persist(t)
            date = manager.find(Date::class.java, t.uuidDate)
        }
        return date
    }

    override fun update(t: Date): Boolean {
        var res = false
        HibernateManager.transaction {
            manager.merge(t)
            res = true
        }
        return res
    }

    override fun delete(t: Date): Boolean {
        var res = false
        HibernateManager.transaction {
            val date = manager.find(Date::class.java, t.uuidDate)
            date.let {
                manager.remove(it)
                res = true
            }
        }
        return res
    }

    override fun getById(id: String?): Date? {
        var res: Date? = null
        HibernateManager.transaction {
            res = manager.find(Date::class.java, id)
        }
        return res
    }

    override fun getAll(): List<Date> {
        var date = mutableListOf<Date>()
        HibernateManager.transaction {
            val query = manager.createNamedQuery("Date.findAll", Date::class.java)
            date = query.resultList
        }
        return date
    }

    override fun getAppointmentsByEmployeeId(employeeId: String): List<Date> {
        var appointments = emptyList<Date>()
        HibernateManager.transaction {
            val query = manager.createNamedQuery("Date.findByEmployeeId", Date::class.java)
            query.setParameter("employeeId", employeeId)
            appointments = query.resultList
        }
        return appointments
    }
}