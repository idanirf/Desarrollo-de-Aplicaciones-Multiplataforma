import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import controllers.ControllerSystem
import controllers.HibernateManager
import models.*
import models.Date
import repositories.date.DateRepositoryImpl
import repositories.employee.EmployeeRepositoyImpl
import repositories.owner.OwnerRepositoryImpl
import repositories.report.ReportRepositoryImpl
import repositories.vehicle.VehiculoRepositoyImpl
import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import kotlin.streams.toList

fun main(args: Array<String>) {
    println("Práctica Hibernate JPA - Kotlin")
    println("Iniciando base de datos...")
    initDatabase()
    println("Base de datos iniciada")
    val dateRepository = DateRepositoryImpl()
    val employeeRepository = EmployeeRepositoyImpl()
    val vehicleRepository = VehiculoRepositoyImpl()
    val ownerRepository = OwnerRepositoryImpl()
    val reportRepositoy = ReportRepositoryImpl()

    val moshi = Moshi.Builder().build()

    // Iniciar controladores
    val controller = ControllerSystem(
        dateRepository,
        employeeRepository,
        vehicleRepository,
        ownerRepository,
        reportRepositoy
    )


    // Cremos un employee
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

    controller.createEmployee(employee)

    // Guardamos el employee
    // controller.employeeRepository.create(employee)
    controller.createEmployee(employee3)
    val yearsOfService = employee.calculateYearsOfService()
    println("Antigüedad del empleado: $yearsOfService años")
    val salaryWithSeniority = employee.calculateSalaryWithSeniority()
    println("Sueldo del empleado con antigüedad: $salaryWithSeniority euros")

    // Obtenemos el employee
    val employee2 = controller.getEmployeeById(employee.uuidEmployee!!)
    println(employee2)

    // Actualizamos el employee
    employee2?.name = "Daniel2"
    controller.updateEmployee(
        employee2?.uuidEmployee!!,
        employee2
    )
    println(controller.getEmployeeById(employee.uuidEmployee!!))

    // Borramos el employee
    controller.deleteEmployee(employee2.uuidEmployee!!)

    // Obtenemos el employee
    println(controller.getEmployeeById(employee.uuidEmployee!!))

    // Cremos un employee
    controller.createEmployee(employee)

    // Creamos un propietario
    val owner = Owner(
        UUID.randomUUID().toString(),
        "12345678A",
        "Daniel",
        "Rodriguez Fernandez",
        "666111222"
    )
    controller.createOwner(owner)
    println(controller.getOwnerById(owner.uuidOwner!!))

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
    controller.createVehicle(vehicle)
    println(controller.getVehicleById(vehicle.uuidVehicle!!))

    // Creamos una cita
    val date = Date(
        UUID.randomUUID().toString(),
        LocalDateTime.now(),
        employee,
        vehicle
    )
    controller.createAppointment(date)
    println(controller.getAppointmentById(date.uuidDate!!))

    // Creamos un reporte
    val report = Report(
        UUID.randomUUID().toString(),
        true,
        12.00,
        1.00,
        true,
        true
    )
    controller.createReport(report)
    println(controller.getReportById(report.uuidReport!!))
    //Actualizamos la cita añadiendo el reporte
    date.report = report
    controller.actualizarCita(
        date.uuidDate!!
    )
    println(controller.getAppointmentById(date.uuidDate!!))


    // Exportar los datos en JSON
    fun jsonWrite(path: String, lista: ArrayList<String>) {
        val fileJson = File(path)
        if (!fileJson.exists()) {
            fileJson.createNewFile()
        }
        val adapter: JsonAdapter<List<String>> =
            moshi.adapter(Types.newParameterizedType(List::class.java, String::class.java))
        fileJson.writeText(adapter.indent(" ").toJson(lista.stream().map { it }.toList()))
    }

    jsonWrite(
        "DataExports/employees.json",
        controller.employeeRepository.getAll().map { it.toString() } as ArrayList<String>)
    jsonWrite(
        "DataExports/vehicles.json",
        controller.vehicleRepository.getAll().map { it.toString() } as ArrayList<String>)
    jsonWrite(
        "DataExports/owners.json",
        controller.ownerRepository.getAll().map { it.toString() } as ArrayList<String>)
    jsonWrite(
        "DataExports/dates.json",
        controller.dateRepository.getAll().map { it.toString() } as ArrayList<String>)
    jsonWrite(
        "DataExports/reports.json",
        controller.reportRepositoy.getAll().map { it.toString() } as ArrayList<String>)

    // Sacar informe
    // Trabajadores que no sean responsables
    val employeeNoResponsable = controller.employeeRepository.getAll().filter { it.responsible != true }
    // Trabajador que mas gana y no es reposanble
    val employeeMaxSalary = employeeNoResponsable.maxByOrNull { it.calculateSalaryWithSeniority() }
    println("Trabajador que más gana sin ser reponsable: $employeeMaxSalary, con un salario de: ${employeeMaxSalary?.calculateSalaryWithSeniority()} €")
    // El salario medio de todos los trabajadores que no son responsables:
    val averageSalary = employeeNoResponsable.map { it.calculateSalaryWithSeniority() }.average()
    println("El salario medio de todos los trabajadores que no son responsables es: $averageSalary €")
    // El salario medio de todos los trabajadores agrupados por especialidad:
    val averageSalaryBySpeciality = controller.employeeRepository.getAll().groupBy { it.speciality }
        .mapValues { it.value.map { it.calculateSalaryWithSeniority() }.average() }
    println("El salario medio de todos los trabajadores agrupados por especialidad es: $averageSalaryBySpeciality")
    //El trabajador/a con menos antigüedad:
    val employeeMinSeniority = controller.employeeRepository.getAll().minByOrNull { it.calculateYearsOfService() }
    println("El trabajador/a con menos antigüedad es: $employeeMinSeniority, con una antigüedad de: ${employeeMinSeniority?.calculateYearsOfService()} años")
    // Trabajadores ordenados por especialidad y ordenados:
    val employeeBySpeciality = controller.employeeRepository.getAll().groupBy { it.speciality }
    println("Trabajadores ordenados por especialidad y ordenados: $employeeBySpeciality")
}


fun initDatabase() {
    HibernateManager.open()
    HibernateManager.close()
}