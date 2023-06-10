package es.drodriguez.itvh2hibernatespringv2.models

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "date")
class Date(
    @Id
    var uuidDate: String? = null,
    var date: LocalDateTime? = null,

    @ManyToOne(targetEntity = Employee::class, optional = true)
    var employee: Employee? = null,

    @ManyToOne(targetEntity = Vehicle::class, optional = true)
    @JoinColumn(name = "vehicle", nullable = true)
    var vehicle: Vehicle? = null,

    @OneToOne(targetEntity = Report::class, optional = true)
    @JoinColumn(name = "report", nullable = true)
    var report: Report? = null
) {
    override fun toString(): String {
        return "Date(uuidDate=$uuidDate, date=$date, employee=$employee, vehicle=$vehicle, report=$report)"
    }
}