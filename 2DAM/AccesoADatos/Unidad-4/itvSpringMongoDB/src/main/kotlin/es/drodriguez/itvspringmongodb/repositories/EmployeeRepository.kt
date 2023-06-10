package es.drodriguez.itvspringmongodb.repositories

import es.drodriguez.itvspringmongodb.models.Employee
import org.bson.types.ObjectId
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface EmployeeRepository: CoroutineCrudRepository<Employee, ObjectId> {
}