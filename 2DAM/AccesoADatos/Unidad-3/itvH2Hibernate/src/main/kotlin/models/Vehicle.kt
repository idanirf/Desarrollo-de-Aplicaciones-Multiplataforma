package models

import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import org.jetbrains.annotations.NotNull
import java.time.LocalDate
import javax.persistence.*

@Entity
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
    @NotNull
    @Cascade(CascadeType.MERGE)
    var owner: Owner? = null
) {
    override fun toString(): String {
        return "Vehicle(uuidVehicle=$uuidVehicle, brand=$brand, model=$model, licensePlate=$licensePlate, dateLicensePlate=$dateLicensePlate, lastRevisionDate=$lastRevisionDate, owner=$owner)"
    }
}