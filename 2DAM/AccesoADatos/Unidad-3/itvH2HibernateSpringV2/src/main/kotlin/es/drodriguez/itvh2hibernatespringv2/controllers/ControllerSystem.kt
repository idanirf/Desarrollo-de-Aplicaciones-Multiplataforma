package es.drodriguez.itvh2hibernatespringv2.controllers

import es.drodriguez.itvh2hibernatespringv2.models.Vehicle
import es.drodriguez.itvh2hibernatespringv2.repositories.date.DateRepository
import es.drodriguez.itvh2hibernatespringv2.repositories.employee.EmployeeRepository
import es.drodriguez.itvh2hibernatespringv2.repositories.owner.OwnerRepository
import es.drodriguez.itvh2hibernatespringv2.repositories.report.ReportRepository
import es.drodriguez.itvh2hibernatespringv2.repositories.vehicle.VehicleRepository
import es.drodriguez.itvh2hibernatespringv2.models.Date
import es.drodriguez.itvh2hibernatespringv2.models.Employee
import es.drodriguez.itvh2hibernatespringv2.models.Owner
import es.drodriguez.itvh2hibernatespringv2.models.Report
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import java.time.LocalDateTime
@Controller
class ControllerSystem
@Autowired constructor(
    val dateRepository: DateRepository,
    val employeeRepository: EmployeeRepository,
    val vehicleRepository: VehicleRepository,
    val ownerRepository: OwnerRepository,
    val reportRepositoy: ReportRepository
) {
    fun createEmployee(employee: Employee): Employee {
        return employeeRepository.save(employee)
    }

    fun updateEmployee(id: String, employee: Employee): Boolean {
        val existingEmployee = employeeRepository.getById(id)
        return run {
            employee.uuidEmployee = existingEmployee.uuidEmployee
            employeeRepository.save(employee)
            true
        }
    }

    fun deleteEmployee(id: String): Boolean {
        val existingEmployee = employeeRepository.getById(id)
        return run {
            employeeRepository.delete(existingEmployee)
            true
        }
    }

    fun getEmployeeById(id: String): Employee? {
        return employeeRepository.getById(id)
    }

    fun createVehicle(vehicle: Vehicle): String {
        val owner = vehicle.owner?.uuidOwner?.let { ownerRepository.getById(it) } ?: return "El propietario especificado no existe."
        if (!isValidLicensePlate(vehicle.licensePlate)) {
            return "La placa del vehículo no es válida."
        }
        vehicleRepository.save(vehicle)
        return "Vehículo creado con éxito."
    }

    fun updateVehicle(id: String, vehicle: Vehicle): String {
        val existingVehicle = vehicleRepository.getById(id) ?: return "El vehículo especificado no existe."
        val owner = vehicle.owner?.uuidOwner?.let { ownerRepository.getById(it) } ?: return "El propietario especificado no existe."
        if (!isValidLicensePlate(vehicle.licensePlate)) {
            return "La placa del vehículo no es válida."
        }
        vehicle.uuidVehicle = existingVehicle.uuidVehicle
        vehicleRepository.save(vehicle)
        return "Vehículo actualizado con éxito."
    }

    fun deleteVehicle(id: String): String {
        val existingVehicle = vehicleRepository.getById(id) ?: return "El vehículo especificado no existe."
        vehicleRepository.delete(existingVehicle)
        return "Vehículo eliminado con éxito."
    }

    fun getVehicleById(id: String): Vehicle? {
        return vehicleRepository.getById(id)
    }

    private fun isValidLicensePlate(licensePlate: String?): Boolean {
        return !licensePlate.isNullOrEmpty()
    }

    fun createOwner(owner: Owner): String {
        if (!isValidDni(owner.dni)) {
            return "El DNI del propietario no es válido."
        }
        ownerRepository.save(owner)
        return "Propietario creado con éxito."
    }

    fun updateOwner(id: String, owner: Owner): String {
        val existingOwner = ownerRepository.getById(id) ?: return "El propietario especificado no existe."
        if (!isValidDni(owner.dni)) {
            return "El DNI del propietario no es válido."
        }
        owner.uuidOwner = existingOwner.uuidOwner
        ownerRepository.save(owner)
        return "Propietario actualizado con éxito."
    }

    fun deleteOwner(id: String): String {
        val existingOwner = ownerRepository.getById(id) ?: return "El propietario especificado no existe."
        ownerRepository.delete(existingOwner)
        return "Propietario eliminado con éxito."
    }

    fun getOwnerById(id: String): Owner? {
        return ownerRepository.getById(id)
    }

    private fun isValidDni(dni: String?): Boolean {
        return !dni.isNullOrEmpty()
    }

    fun createAppointment(appointment: Date): String {
        val vehicle = appointment.vehicle?.uuidVehicle?.let { vehicleRepository.getById(it) }
            ?: return "El vehículo especificado no existe."
        val employee = appointment.employee?.uuidEmployee?.let { employeeRepository.getById(it) }
            ?: return "El empleado especificado no existe."
        if (!isValidDateTime(appointment.date)) {
            return "La fecha y hora de la cita no son válidas."
        }

        // Comprobamos si el vehículo tiene más citas hoy
        val appointmentsForVehicle = dateRepository.findAll().filter {
            it.vehicle?.licensePlate == vehicle.licensePlate && it.date?.toLocalDate() == appointment.date?.toLocalDate()
        }
        if (appointmentsForVehicle.isNotEmpty()) {
            return "El vehículo ya tiene una cita programada para hoy. No se puede obtener más de una cita al día."
        }

        // Comprobamos si el empleado ha alcanzado el límite de citas en el mismo horario
        val appointmentsByEmployee = dateRepository.findAll().filter {
            it.employee?.uuidEmployee == employee.uuidEmployee && it.date?.hour == appointment.date?.hour && it.date?.minute == appointment.date?.minute
        }
        if (appointmentsByEmployee.size >= 4) {
            return "El empleado ya tiene 4 citas programadas en el mismo horario. No se puede programar más citas en este momento."
        }

        dateRepository.save(appointment)
        return "Cita creada con éxito."
    }


    fun actualizarCita(id: String, report: Report): String {
        val existingAppointment = dateRepository.getById(id) ?: return "La cita especificada no existe."
        existingAppointment.report = report
        dateRepository.save(existingAppointment)
        return "Cita actualizada con éxito."
    }



    fun deleteAppointment(id: String): String {
        val existingAppointment = dateRepository.getById(id) ?: return "La cita especificada no existe."
        dateRepository.delete(existingAppointment)
        return "Cita eliminada con éxito."
    }

    fun getAppointmentById(id: String): Date? {
        return dateRepository.getById(id)
    }

    private fun isValidDateTime(dateTime: LocalDateTime?): Boolean {
        return dateTime != null
    }

    fun getReportById(id: String): Report? {
        return reportRepositoy.getById(id)
    }

    private fun isValidRating(rating: Double?): Boolean {
        return rating != null && rating >= 1 && rating <= 10
    }

    fun deleteReport(id: String): String {
        val existingReport = reportRepositoy.getById(id) ?: return "El informe especificado no existe."
        reportRepositoy.delete(existingReport)
        return "Informe eliminado con éxito."
    }

    fun createReport(report: Report): String {
        reportRepositoy.save(report)
        return "Informe creado con éxito."
    }

    fun checkVehicleExists(uuidVehicle: String, vehicleRepository: VehicleRepository): Boolean {
        val vehicle = vehicleRepository.getById(uuidVehicle)
        return true
    }

    fun findAllEmployees(): List<Employee> {
        return employeeRepository.findAll()
    }
}