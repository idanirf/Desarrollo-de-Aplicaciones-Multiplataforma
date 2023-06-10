package es.drodriguez.itvspringmongodb

import es.drodriguez.itvspringmongodb.controllers.SystemController
import es.drodriguez.itvspringmongodb.models.*
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.LocalDate

@SpringBootApplication
class ItvSpringMongoDbApplication
@Autowired constructor(
    private val controller: SystemController
) : CommandLineRunner {
    override fun run(vararg args: String?): Unit = runBlocking {
        println("Hello Spring Data Reactive MongoDB")
        println("Created Employee")

        var employee = Employee(
            name = "Daniel",
            telf = "123456789",
            email = "danie@daniel.es",
            username = "idanirf",
            password = "1234",
            dataHiring = LocalDate.of(2020, 1, 1),
            speciality = Speciality.ENGINE
        )
        val owner = Owner(
            dni = "12345678A",
            name = "Daniel",
            surnames = "Rodriguez Fernandez",
            telf = "666111222"
        )
        val vehicle = Vehicle(
            brand = "Seat",
            model = "Ibiza",
            licensePlate = "1234SSS",
            dateLicensePlate = LocalDate.now(),
            lastRevisionDate = "2023-01-14",
            owner = owner
        )
        val report = Report(
            date = LocalDate.now(),
            vehicle = vehicle,
            employee = employee,
            competent = true,
            braking = 12.00,
            pollution = 1.00,
            brakingCompetent = true,
            lightsCompetent = true
        )


        val insert = launch {
            controller.saveEmployee(employee)
            println("Find By Id Employees")
            println(controller.findEmployeeById(employee.id))

            controller.saveOwner(owner)

            println("Find By Id Owners")
            println(controller.findOwnerById(owner.id))

            controller.saveVehicle(vehicle)
            println(controller.findVehicleById(vehicle.id))

            controller.saveReport(report)
            println(controller.findReportById(report.id))

            println("Find All")
            println(controller.findAllEmployees().toList())
            println(controller.findAllOwners().toList())
            println(controller.findAllVehicles().toList())
            println(controller.findAllReports().toList())

            println("Delete Empleado")
            controller.deleteEmployee(employee)
            println(controller.findEmployeeById(employee.id))

        }
        insert.join()
    }
}

fun main(args: Array<String>) {
    runApplication<ItvSpringMongoDbApplication>(*args)
}
