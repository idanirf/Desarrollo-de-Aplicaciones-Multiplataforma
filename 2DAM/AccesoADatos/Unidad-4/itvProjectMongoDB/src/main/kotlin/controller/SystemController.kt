package controller

import models.Employee
import models.Owner
import models.Report
import models.Vehicle
import repositories.employee.EmployeeRepository
import repositories.owner.OwnerRepository
import repositories.report.ReportRepository
import repositories.vehicle.VehicleRepository

class SystemController(
    private val vehicleRepository: VehicleRepository,
    private val reportRepository: ReportRepository,
    private val employeeRepository: EmployeeRepository,
    private val ownerRepository: OwnerRepository
) {
    fun findAllEmployees(): List<Employee> {
        return employeeRepository.findAll()
    }

    fun findAllOwners(): List<Owner> {
        return ownerRepository.findAll()
    }

    fun findAllVehicles(): List<Vehicle> {
        return vehicleRepository.findAll()
    }

    fun findAllReports(): List<Report> {
        return reportRepository.findAll()
    }

    fun saveEmployee(employee: Employee) {
        employeeRepository.save(employee)
    }

    fun saveOwner(owner: Owner) {
        ownerRepository.save(owner)
    }

    fun saveVehicle(vehicle: Vehicle) {
        vehicleRepository.save(vehicle)
    }

    fun saveReport(report: Report) {
        reportRepository.save(report)
    }

    fun deleteEmployee(employee: Employee) {
        employeeRepository.delete(employee)
    }

    fun deleteOwner(owner: Owner) {
        ownerRepository.delete(owner)
    }

    fun deleteVehicle(vehicle: Vehicle) {
        vehicleRepository.delete(vehicle)
    }

    fun deleteReport(report: Report) {
        reportRepository.delete(report)
    }

    fun findEmployeeById(id: String): Employee? {
        return employeeRepository.findById(id)
    }

    fun findOwnerById(id: String): Owner? {
        return ownerRepository.findById(id)
    }

    fun findVehicleById(id: String): Vehicle? {
        return vehicleRepository.findById(id)
    }

    fun findReportById(id: String): Report? {
        return reportRepository.findById(id)
    }
}