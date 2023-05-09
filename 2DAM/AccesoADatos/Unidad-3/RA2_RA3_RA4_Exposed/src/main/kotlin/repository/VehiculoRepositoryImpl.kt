package repository

import entities.PersonasDao
import entities.VehiculoDao
import mapper.toVehiculo
import models.Vehiculo
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class VehiculoRepositoryImpl: CrudRepository<Vehiculo, UUID> {
    val vehiculo = VehiculoDao
    var persona = PersonasDao

    override fun findAll(): List<Vehiculo> {
        var p: List<Vehiculo> = listOf()
        transaction {
            p = vehiculo.all().map {it.toVehiculo()}
        }
        return p
    }

    override fun create(t: Vehiculo): Vehiculo? {
        var p: Vehiculo? = null
        transaction {
            p = vehiculo.new(t.uuid){
                marca = t.marca.toString()
                modelo = t.modelo.toString()
                matricula = t.matricula.toString()
                persona = t.persona
            }.toVehiculo()
        }
        return p
    }

    override fun update(t: Vehiculo): Boolean {
        var p: VehiculoDao? = null
        var exists: Boolean = false
        transaction {
            p = vehiculo.findById(t.uuid)
            p?.apply {
                marca = t.marca.toString()
                modelo = t.modelo.toString()
                matricula = t.matricula.toString()
            }
            exists = true
        }
        println(p)
        return exists
    }

    override fun deleteById(id: UUID): Boolean {
        var p: Vehiculo? = null
        transaction {
            p = vehiculo.findById(id)?.toVehiculo()
        }
        return true
    }

    override fun findById(id: UUID): Vehiculo? {
        var p: Vehiculo? = null
        transaction {
            p = vehiculo.findById(id)?.toVehiculo()
        }
        return p
    }
}