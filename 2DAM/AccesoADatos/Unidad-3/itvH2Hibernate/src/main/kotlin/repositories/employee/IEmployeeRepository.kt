package repositories.employee

import models.Employee
import repositories.ICrudRepository

interface IEmployeeRepository: ICrudRepository<Long, Employee> {
}