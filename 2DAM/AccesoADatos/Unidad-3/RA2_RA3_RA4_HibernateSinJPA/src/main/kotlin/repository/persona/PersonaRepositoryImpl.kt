package repository.persona

import Controller.HibernateManager
import Controller.HibernateManager.manager
import models.Persona
import javax.persistence.TypedQuery

class PersonaRepositoryImpl: IPersonaRepository {
    override fun create(t: Persona): Persona {
        var persIdAct: Persona = t
        HibernateManager.transaction {
            manager.persist(t)
            persIdAct = manager.find(Persona::class.java, t.id)
        }
        return persIdAct
    }

    override fun update(t: Persona): Boolean {
        var res = false
        HibernateManager.transaction {
            manager.merge(t)
            res = true
        }
        return res
    }

    override fun delete(t: Persona): Boolean {
        var res = false
        HibernateManager.transaction {
            val pers = manager.find(Persona::class.java, t.id)
            pers?.let {
                manager.remove(it)
                res = true
            }
        }
        return res
    }

    override fun getById(id: Long): Persona? {
        var persona: Persona? = null
        HibernateManager.transaction {
            persona = manager.find(Persona::class.java, id)
        }
        return persona
    }

    override fun getAll(): List<Persona> {
        var personas = mutableListOf<Persona>()
        HibernateManager.query {
            val query: TypedQuery<Persona> = manager.createNamedQuery("Persona.findAll", Persona::class.java)
            personas = query.resultList
        }
        return personas
    }
}