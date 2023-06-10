package models

import kotlinx.serialization.Contextual
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@NamedQuery(name = "Date.findAll", query = "SELECT d FROM Date d")
@NamedQuery(name = "Date.findByEmployeeId", query = "SELECT d FROM Date d WHERE d.employee.id = :employeeId")

class Date(
    @Id
    var uuidDate: String? = null,
    @Contextual
    var date: LocalDateTime? = null,

    @ManyToOne(targetEntity = Employee::class, optional = true)
    @JoinColumn(name = "employee", nullable = true)
    @NotNull
    var employee: Employee? = null,


    @ManyToOne(targetEntity = Vehicle::class, optional = true)
    @JoinColumn(name = "vehicle", nullable = true)
    @NotNull
    var vehicle: Vehicle? = null,

    @OneToOne(targetEntity = Report::class, optional = true)
    @JoinColumn(name = "report", nullable = true)
    @NotNull

    var report: Report? = null
) {
    override fun toString(): String {
        return "Date(uuidDate=$uuidDate, date=$date, employee=$employee, vehicle=$vehicle, report=$report)"
    }
}