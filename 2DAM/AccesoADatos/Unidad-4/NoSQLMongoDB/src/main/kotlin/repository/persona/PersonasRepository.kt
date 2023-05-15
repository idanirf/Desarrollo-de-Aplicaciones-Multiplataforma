package repository.persona

import db.DBManager
import mappers.toDocument
import mappers.toPersona
import models.Persona
import org.bson.Document

class PersonasRepository(): IPersonaRepository {

    private val collection = DBManager.db.getCollection("personas")


    override fun create(t: Persona) {
        t.toDocument()?.let { collection.insertOne(it) }
    }

    override fun findById(uuid: String): Persona? {
        val sq = Document("uuid", uuid)
        var pers: Persona? = null
        try {
            val res = collection.find(sq).first()
            pers = res.toPersona()

        } catch (e: Exception) {
            println("Persona no localizada en la base de datos")
        }
        return pers
    }

    override fun findAll(): List<Persona> {
        val listPers = mutableListOf<Persona>()
        for (pers in collection.find()) {
            val per = pers.toPersona()
            if (per!= null) {
                listPers.add(per)
            }
        }
        return listPers
    }

    override fun delete(uuid: String) {
        val sq = Document("uuid", uuid)
        collection.deleteOne(sq)
    }

    override fun deleteAll() {
        for(persona in collection.find()){
            collection.deleteOne(Document("uuid", persona.getString("uuid")))
        }
    }

    override fun update(t: Persona) {
        val sq = Document("uuid", t.uuid)
        t.toDocument()?.let { collection.updateOne(sq, it) }
    }
}