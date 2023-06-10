package controllers

import models.*
import repositories.date.IDateRepository
import repositories.employee.IEmployeeRepository
import repositories.owner.IOwnerRepository
import repositories.report.IReportRepository
import repositories.vehicle.IVehicleRepository
import java.time.LocalDateTime

class ControllerSystem(
    val dateRepository: IDateRepository,
    val employeeRepository: IEmployeeRepository,
    val vehicleRepository: IVehicleRepository,
    val ownerRepository: IOwnerRepository,
    val reportRepositoy: IReportRepository
) {
    fun createEmployee(employee: Employee): Employee {
        return employeeRepository.create(employee)
    }

    fun updateEmployee(id: String, employee: Employee): Boolean {
        val existingEmployee = employeeRepository.getById(id)
        return if (existingEmployee != null) {
            employee.uuidEmployee = existingEmployee.uuidEmployee
            employeeRepository.update(employee)
            true
        } else {
            false
        }
    }

    fun deleteEmployee(id: String): Boolean {
        val existingEmployee = employeeRepository.getById(id)
        return if (existingEmployee != null) {
            employeeRepository.delete(existingEmployee)
            true
        } else {
            false
        }
    }

    fun getEmployeeById(id: String): Employee? {
        return employeeRepository.getById(id)
    }

    fun createVehicle(vehicle: Vehicle): String {
        val owner = ownerRepository.getById(vehicle.owner?.uuidOwner) ?: return "El propietario especificado no existe."
        if (!isValidLicensePlate(vehicle.licensePlate)) {
            return "La placa del vehículo no es válida."
        }
        vehicleRepository.create(vehicle)
        return "Vehículo creado con éxito."
    }

    fun updateVehicle(id: String, vehicle: Vehicle): String {
        val existingVehicle = vehicleRepository.getById(id) ?: return "El vehículo especificado no existe."
        val owner = ownerRepository.getById(vehicle.owner?.uuidOwner) ?: return "El propietario especificado no existe."
        if (!isValidLicensePlate(vehicle.licensePlate)) {
            return "La placa del vehículo no es válida."
        }
        vehicle.uuidVehicle = existingVehicle.uuidVehicle
        vehicleRepository.update(vehicle)
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
        ownerRepository.create(owner)
        return "Propietario creado con éxito."
    }

    fun updateOwner(id: String, owner: Owner): String {
        val existingOwner = ownerRepository.getById(id) ?: return "El propietario especificado no existe."
        if (!isValidDni(owner.dni)) {
            return "El DNI del propietario no es válido."
        }
        owner.uuidOwner = existingOwner.uuidOwner
        ownerRepository.update(owner)
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
        val vehicle = vehicleRepository.getById(appointment.vehicle?.uuidVehicle)
            ?: return "El vehículo especificado no existe."
        val employee = employeeRepository.getById(appointment.employee?.uuidEmployee)
            ?: return "El empleado especificado no existe."
        if (!isValidDateTime(appointment.date)) {
            return "La fecha y hora de la cita no son válidas."
        }

        // Comprobamos si el vehículo tiene más citas hoy
        val appointmentsForVehicle = dateRepository.getAll().filter {
            it.vehicle?.licensePlate == vehicle.licensePlate && it.date?.toLocalDate() == appointment.date?.toLocalDate()
        }
        if (appointmentsForVehicle.isNotEmpty()) {
            return "El vehículo ya tiene una cita programada para hoy. No se puede obtener más de una cita al día."
        }

        // Comprobamos si el empleado ha alcanzado el límite de citas en el mismo horario
        val appointmentsByEmployee = dateRepository.getAll().filter {
            it.employee?.uuidEmployee == employee.uuidEmployee && it.date?.hour == appointment.date?.hour && it.date?.minute == appointment.date?.minute
        }
        if (appointmentsByEmployee.size >= 4) {
            return "El empleado ya tiene 4 citas programadas en el mismo horario. No se puede programar más citas en este momento."
        }

        dateRepository.create(appointment)
        return "Cita creada con éxito."
    }


    fun actualizarCita(id: String): String {
        val existingAppointment = dateRepository.getById(id) ?: return "La cita especificada no existe."
        dateRepository.update(existingAppointment)
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
        if (!isValidRating(report.braking)) {
            return "El valor de frenado no es válido."
        }
        if (!isValidRating(report.pollution)) {
            return "El valor de contaminación no es válido."
        }
        reportRepositoy.create(report)
        return "Informe creado con éxito."
    }

    fun checkVehicleExists(uuidVehicle: String, vehicleRepository: IVehicleRepository): Boolean {
        val vehicle = vehicleRepository.getById(uuidVehicle)
        return vehicle != null
    }
}

