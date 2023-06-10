package es.drodriguez.itvh2hibernatespringv2.repositories.employee

import es.drodriguez.itvh2hibernatespringv2.models.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository: JpaRepository<Employee, String> {
}