package repository

import entities.PersonasDao
import mapper.toPersona
import models.Persona
import org.jetbrains.exposed.sql.transactions.transaction

class PersonaRepositoryImpl: CrudRepository<Persona, Long> {

    val persona = PersonasDao

    override fun findAll(): List<Persona> {
        var p: List<Persona> = listOf()
        transaction {
            p = persona.all().map { it.toPersona() }
        }
        return p
    }

    override fun create(t: Persona): Persona? {
        var p: Persona? = null
        transaction {
            p = persona.new(){
                nombre = t.nombre
                fechaCarnet = t.fechaCarntet
            }.toPersona()
        }
        return p
    }

    override fun update(t: Persona): Boolean {
        var p: PersonasDao? = null
        var exists: Boolean = false
        transaction {
            p = persona.findById(t.id)
            p?.apply {
                nombre = t.nombre
                fechaCarnet = t.fechaCarntet
            }
            exists = true
        }
        println(p)
        return exists
    }

    override fun deleteById(id: Long): Boolean {
        var p: Persona? = null
        transaction {
            p = persona.findById(id)?.toPersona()
        }
        if (p== null) {
            return false
        } else {
            transaction {
                persona.findById(id)?.delete()
            }
        }
        return true
    }

    override fun findById(id: Long): Persona? {
        var p: Persona? = null
        transaction {
            p = persona.findById(id)?.toPersona()
        }
        return p
    }
}