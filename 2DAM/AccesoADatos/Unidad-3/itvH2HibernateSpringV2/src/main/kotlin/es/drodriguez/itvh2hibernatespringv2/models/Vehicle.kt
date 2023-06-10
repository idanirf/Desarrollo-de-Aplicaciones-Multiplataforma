package es.drodriguez.itvh2hibernatespringv2.models

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name="vehicle")
@NamedQuery(name = "Vehicle.findAll", query = "SELECT u FROM Vehicle u")
class Vehicle(
    @Id
    var uuidVehicle: String? = null,
    var brand: String? = null,
    var model: String? = null,
    var licensePlate: String? = null,
    var dateLicensePlate: LocalDate? = null,
    var lastRevisionDate: String? = null,

    @ManyToOne(targetEntity = Owner::class, optional = true)
    @JoinColumn(name = "owner", nullable = true)
    var owner: Owner? = null
) {
    override fun toString(): String {
        return "Vehicle(uuidVehicle=$uuidVehicle, brand=$brand, model=$model, licensePlate=$licensePlate, dateLicensePlate=$dateLicensePlate, lastRevisionDate=$lastRevisionDate, owner=$owner)"
    }
}