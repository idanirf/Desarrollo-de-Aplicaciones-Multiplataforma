package repositories.employee

import db.MongoDbManager
import models.Employee
import org.bson.Document
import org.litote.kmongo.deleteOneById
import org.litote.kmongo.findOneById
import org.litote.kmongo.getCollection
import org.litote.kmongo.save

class EmployeeRepository: IEmployeeRepository {
    override fun findAll(): List<Employee> {
        return MongoDbManager.database.getCollection<Employee>().find().toList()
    }

    override fun findById(id: String): Employee? {
        return MongoDbManager.database.getCollection<Employee>().findOneById(id)
    }

    override fun save(entity: Employee): Employee {
        MongoDbManager.database.getCollection<Employee>().save(entity)
        return entity
    }

    override fun delete(entity: Employee): Boolean {
        return MongoDbManager.database.getCollection<Employee>().deleteOneById(entity.id).wasAcknowledged()
    }

    private fun update(entity: Employee): Employee {
        MongoDbManager.database.getCollection<Employee>().save(entity)
        return entity
    }
}