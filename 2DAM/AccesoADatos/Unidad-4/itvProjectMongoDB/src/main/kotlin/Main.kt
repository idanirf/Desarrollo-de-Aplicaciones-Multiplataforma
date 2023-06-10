import controller.SystemController
import db.MongoDbManager
import models.*
import repositories.employee.EmployeeRepository
import repositories.owner.OwnerRepository
import repositories.report.ReportRepository
import repositories.vehicle.VehicleRepository
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

fun main(args: Array<String>) {
    val employeeRepository = EmployeeRepository()
    val vehicleRepository = VehicleRepository()
    val ownerRepository = OwnerRepository()
    val reportRepositoy = ReportRepository()

    val controller = SystemController(
        vehicleRepository,
        reportRepositoy,
        employeeRepository,
        ownerRepository
    )

    var employee = Employee(
        UUID.randomUUID().toString(),
        "Daniel",
        "123456789",
        "danie@daniel.es",
        "idanirf",
        "1234",
        LocalDate.of(2020, 1, 1),
        Speciality.ENGINE
    )

    var employee3 = Employee(
        UUID.randomUUID().toString(),
        "Azahara",
        "123456789",
        "aza@aza.es",
        "azabl",
        "1234",
        LocalDate.of(2020, 1, 1),
        Speciality.INSIDE,
        true
    )

    controller.saveEmployee(employee)


    // Guardamos el employee
    controller.saveEmployee(employee3)
    val yearsOfService = employee.calculateYearsOfService()
    println("Antigüedad del empleado: $yearsOfService años")
    val salaryWithSeniority = employee.calculateSalaryWithSeniority()
    println("Sueldo del empleado con antigüedad: $salaryWithSeniority euros")

    // Obtenemos el employee
    val employee2 = controller.findEmployeeById(employee.id)
    println(employee2)



    // Borramos el employee
    controller.deleteEmployee(employee3)

    // Obtenemos el employee
    println(controller.findEmployeeById(employee.id))

    // Cremos un employee
    controller.saveEmployee(employee)


    // Creamos un propietario
    val owner = Owner(
        UUID.randomUUID().toString(),
        "12345678A",
        "Daniel",
        "Rodriguez Fernandez",
        "666111222"
    )
    controller.saveOwner(owner)
    println(controller.findOwnerById(owner.id))

    // Creamos un vehiculo
    val vehicle = Vehicle(
        UUID.randomUUID().toString(),
        "Seat",
        "Ibiza",
        "1234SSS",
        LocalDate.now(),
        "2023-01-14",
        owner

    )
    controller.saveVehicle(vehicle)
    println(controller.findVehicleById(vehicle.id))

// Creamos un reporte
    val report = Report(
        UUID.randomUUID().toString(),
        LocalDate.now(),
        vehicle,
        employee,
        true,
        12.00,
        1.00,
        true,
        true
    )
    controller.saveReport(report)
    println(controller.findReportById(report.id))
}
