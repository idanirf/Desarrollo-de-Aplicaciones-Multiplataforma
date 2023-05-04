package models

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.NamedQuery

@Entity
@NamedQuery(name = "Vehiculo.findAll", query = "SELECT t FROM Vehiculo t")
class Vehiculo(
    @Id
    var uuid: String = "",
    var marca: String = "",
    var modelo: String = "",
    var matricula: String = "",
    var fecha: LocalDate = LocalDate.now(),
    var motor: String = "",
    var createdAt: LocalDate = LocalDate.now(),
    var updatedAt: LocalDate = LocalDate.now(),
    var deleted: Boolean = false,

    @ManyToOne(optional = true)
    @JoinColumn(name = "persona", nullable = true)
    var persona: Persona? = null
){
    override fun toString(): String {
        return "Vehiculo(uuid=$uuid, marca=$marca, modelo=$modelo, matricula=$matricula, fecha=$fecha, " +
                "motor=$motor, createdAt=$createdAt, updatedAt=$updatedAt, deleted=$deleted, persona=$persona)"
    }
}
