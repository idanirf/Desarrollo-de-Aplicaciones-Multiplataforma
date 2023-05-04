package models

import java.time.LocalDate
import javax.persistence.*

@Entity
@NamedQuery(name = "Persona.findAll", query = "SELECT t FROM Persona t")
class Persona(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var nombre: String = "",
    var fechaPermisoConduccion: LocalDate = LocalDate.now(),

    @OneToMany(mappedBy = "persona", orphanRemoval = false)
    var listVehiculo: List<Vehiculo> = listOf()
){
    override fun toString(): String {
        return "Persona(id=$id, nombre='$nombre', fechaPermisoConduccion=$fechaPermisoConduccion)"
    }
}
