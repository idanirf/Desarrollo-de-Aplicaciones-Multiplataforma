package repositories.employee

import controllers.HibernateManager
import controllers.HibernateManager.manager
import models.Employee

class EmployeeRepositoyImpl: IEmployeeRepository {
    override fun create(t: Employee): Employee {
        var employee: Employee = t
        HibernateManager.transaction {
            manager.persist(t)
            employee = manager.find(Employee::class.java, t.uuidEmployee)
        }
        return employee
    }

    override fun update(t: Employee): Boolean {
        var res = false
        HibernateManager.transaction {
            manager.merge(t)
            res = true
        }
        return res
    }

    override fun delete(t: Employee): Boolean {
        var res = false
        HibernateManager.transaction {
            val employees = manager.find(Employee::class.java, t.uuidEmployee)
            employees.let {
                manager.remove(it)
                res = true
            }
        }
        return res
    }

    override fun getById(id: String?): Employee? {
        var res: Employee? = null
        HibernateManager.transaction {
            res = manager.find(Employee::class.java, id)
        }
        return res
    }

    override fun getAll(): List<Employee> {
        var employee = mutableListOf<Employee>()
        HibernateManager.query {
            val query = manager.createNamedQuery("Employee.findAll",Employee::class.java)
            employee = query.resultList
        }
        return employee
    }
}