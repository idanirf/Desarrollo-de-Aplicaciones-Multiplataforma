package repositories.employee

import models.Employee
import repositories.ICrudRepository

interface IEmployeeRepository: ICrudRepository<Employee, String> {
}