package es.drodriguez.itvh2hibernatespringv2

import es.drodriguez.itvh2hibernatespringv2.controllers.ControllerSystem
import es.drodriguez.itvh2hibernatespringv2.models.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import es.drodriguez.itvh2hibernatespringv2.models.Employee
import es.drodriguez.itvh2hibernatespringv2.models.Owner
import es.drodriguez.itvh2hibernatespringv2.models.Report
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class ItvH2HibernateSpringV2Application
constructor(
    @Autowired private val controller: ControllerSystem
): CommandLineRunner{
    override fun run(vararg args: String) {
        println("Práctica Hibernate JPA - Kotlin")

        println("Base de datos iniciada")

        // Cremos un employee
        var employee = Employee(
           "f4ce037b-1bb6-4514-ad5f-4df8c0587f39",
            "Daniel",
            "123456789",
            "danie@daniel.es",
            "idanirf",
            "1234",
            LocalDate.of(2020, 1, 1),
            Speciality.ENGINE
        )

        controller.createEmployee(employee)

        // Guardamos el employee
        val yearsOfService = employee.calculateYearsOfService()
        println("Antigüedad del empleado: $yearsOfService años")
        val salaryWithSeniority = employee.calculateSalaryWithSeniority()
        println("Sueldo del empleado con antigüedad: $salaryWithSeniority euros")

        // Obtenemos el employee
        val employee2 = controller.getEmployeeById("f4ce037b-1bb6-4514-ad5f-4df8c0587f39")
        println(employee2)

        // FindALL
        println("Aquí hago el FindALL empleado")
        println(controller.findAllEmployees())

        // Actualizamos el employee
        employee2?.name = "Daniel2"
        controller.updateEmployee(
                "f4ce037b-1bb6-4514-ad5f-4df8c0587f39",
                employee2!!
        )
       println(controller.getEmployeeById("f4ce037b-1bb6-4514-ad5f-4df8c0587f39"))

        // Creamos un propietario
        val owner = Owner(
            "d5a8a21a-863c-475b-a8b2-9e4e11668b3a",
            "12345678A",
            "Daniel",
            "Rodriguez Fernandez",
            "666111222"
        )
        controller.createOwner(owner)
        println(controller.getOwnerById(owner.uuidOwner!!))

        // Creamos un vehiculo
        val vehicle = Vehicle(
           "4f6d4f8b-1337-4b42-b05e-d95e1b7d72c9",
            "Seat",
            "Ibiza",
            "1234SSS",
            LocalDate.now(),
            "2023-01-14",
            owner
        )
        controller.createVehicle(vehicle)
        println(controller.getVehicleById("4f6d4f8b-1337-4b42-b05e-d95e1b7d72c9"))

        // Creamos una cita
        val date = es.drodriguez.itvh2hibernatespringv2.models.Date(
            "ce859f7e-5f35-4c47-b835-9a0623b4fe51",
            LocalDateTime.now(),
            employee,
            vehicle,
                report = null
        )
        controller.createAppointment(date)
        //FindAll de cita
        println(controller.dateRepository.findAll())
        println(controller.getAppointmentById(
                "ce859f7e-5f35-4c47-b835-9a0623b4fe51"
        ))

        // Creamos un reporte
        val report = Report(
            "f4ce037b-1bb6-4514-ad5f-4df8c0587f39",
            true,
            12.00,
            1.00,
            true,
                lightsCompetent = true
        )
        controller.createReport(report)
        println("FindAll de reporte")
        println(controller.reportRepositoy.findAll())
        println("Buscar report por id")
        println(controller.getReportById("f4ce037b-1bb6-4514-ad5f-4df8c0587f39"))

        controller.actualizarCita("ce859f7e-5f35-4c47-b835-9a0623b4fe51", report)

        //Obtenemos de nuevo la cita
        println(controller.getAppointmentById("ce859f7e-5f35-4c47-b835-9a0623b4fe51"))

        // Sacar informe
        // Trabajadores que no sean responsables
        val employeeNoResponsable = controller.employeeRepository.findAll().filter { it.responsible != true }
        // Trabajador que mas gana y no es reposanble
        val employeeMaxSalary = employeeNoResponsable.maxByOrNull { it.calculateSalaryWithSeniority() }
        println("Trabajador que más gana sin ser reponsable: $employeeMaxSalary, con un salario de: ${employeeMaxSalary?.calculateSalaryWithSeniority()} €")
        // El salario medio de todos los trabajadores que no son responsables:
        val averageSalary = employeeNoResponsable.map { it.calculateSalaryWithSeniority() }.average()
        println("El salario medio de todos los trabajadores que no son responsables es: $averageSalary €")
        // El salario medio de todos los trabajadores agrupados por especialidad:
        val averageSalaryBySpeciality = controller.employeeRepository.findAll().groupBy { it.speciality }
            .mapValues { it.value.map { it.calculateSalaryWithSeniority() }.average() }
        println("El salario medio de todos los trabajadores agrupados por especialidad es: $averageSalaryBySpeciality")
        //El trabajador/a con menos antigüedad:
        val employeeMinSeniority = controller.employeeRepository.findAll().minByOrNull { it.calculateYearsOfService() }
        println("El trabajador/a con menos antigüedad es: $employeeMinSeniority, con una antigüedad de: ${employeeMinSeniority?.calculateYearsOfService()} años")
        // Trabajadores ordenados por especialidad y ordenados:
        val employeeBySpeciality = controller.employeeRepository.findAll().groupBy { it.speciality }
        println("Trabajadores ordenados por especialidad y ordenados: $employeeBySpeciality")
    }
}
fun main(args: Array<String>) {
    runApplication<ItvH2HibernateSpringV2Application>(*args)
}
