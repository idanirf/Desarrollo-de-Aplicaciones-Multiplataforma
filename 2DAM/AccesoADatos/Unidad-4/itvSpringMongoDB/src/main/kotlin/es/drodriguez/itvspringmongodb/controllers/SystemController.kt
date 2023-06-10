package es.drodriguez.itvspringmongodb.controllers

import es.drodriguez.itvspringmongodb.models.Employee
import es.drodriguez.itvspringmongodb.models.Owner
import es.drodriguez.itvspringmongodb.models.Report
import es.drodriguez.itvspringmongodb.models.Vehicle
import es.drodriguez.itvspringmongodb.repositories.EmployeeRepository
import es.drodriguez.itvspringmongodb.repositories.OwnerRepository
import es.drodriguez.itvspringmongodb.repositories.ReportRepository
import es.drodriguez.itvspringmongodb.repositories.VehicleRepository
import kotlinx.coroutines.flow.Flow
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class SystemController
@Autowired constructor(
    private val employeeRepository: EmployeeRepository,
    private val ownerRepository: OwnerRepository,
    private val reportRepository: ReportRepository,
    private val vehicleRepository: VehicleRepository
) {
    fun findAllEmployees(): Flow<Employee> {
        return employeeRepository.findAll()
    }

    fun findAllOwners(): Flow<Owner> {
        return ownerRepository.findAll()
    }

    fun findAllVehicles(): Flow<Vehicle> {
        return vehicleRepository.findAll()
    }

    fun findAllReports(): Flow<Report> {
        return reportRepository.findAll()
    }

    suspend fun saveEmployee(employee: Employee) {
        employeeRepository.save(employee)
    }

    suspend fun saveOwner(owner: Owner) {
        ownerRepository.save(owner)
    }

    suspend fun saveVehicle(vehicle: Vehicle) {
        vehicleRepository.save(vehicle)
    }

    suspend fun saveReport(report: Report) {
        reportRepository.save(report)
    }

    suspend fun deleteEmployee(employee: Employee) {
        employeeRepository.delete(employee)
    }

    suspend fun deleteOwner(owner: Owner) {
        ownerRepository.delete(owner)
    }

    suspend fun deleteVehicle(vehicle: Vehicle) {
        vehicleRepository.delete(vehicle)
    }

    suspend fun deleteReport(report: Report) {
        reportRepository.delete(report)
    }

   suspend fun findEmployeeById(id: ObjectId): Employee? {
        return employeeRepository.findById(id)
    }

   suspend fun findOwnerById(id: ObjectId): Owner? {
        return ownerRepository.findById(id)
    }

    suspend fun findVehicleById(id: ObjectId): Vehicle? {
        return vehicleRepository.findById(id)
    }

    suspend fun findReportById(id: ObjectId): Report? {
        return reportRepository.findById(id)
    }

}