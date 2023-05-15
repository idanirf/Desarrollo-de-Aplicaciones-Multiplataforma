package repository.vehiculo

import db.DBManager
import mappers.ToDocument
import mappers.toVehiculo
import models.Vehiculo
import org.bson.Document

class VehiculoRepository(): IVehiculoRepository {

    private val collection = DBManager.db.getCollection("vehiculos")
    override fun findAllByPersonaId(personaId: String): List<Vehiculo> {
        var sq = Document("persona_id", personaId)
        var lista = ArrayList<Vehiculo>()
        var res = collection.find(sq).forEach{
            var v = it.toVehiculo()
            if (v != null) {lista.add(v)}
        }
        return lista
    }

    override fun create(t: Vehiculo) {
        collection.insertOne(Document("uuid", t.uuid)
            .append("marca", t.marca)
            .append("modelo", t.modelo)
            .append("matricula", t.matricula)
            .append("uuidPersona", t.uuidPersona)
        )
    }

    override fun findById(uuid: String): Vehiculo? {
        var vehiculo: Vehiculo? = null
        try {
            val vehiculo = collection.find(Document("uuid", uuid)).first()?.toVehiculo()
            return vehiculo
        } catch (e: Exception) {
            println("Vehiculo no encontrado")
        }
        return null
    }

    override fun findAll(): List<Vehiculo> {
        var res = ArrayList<Vehiculo>()
        collection.find().forEach {
            var vehiculo = it.toVehiculo()
            if (vehiculo != null) {
                res.add(vehiculo)
            }
        }
        return res
    }

    override fun delete(uuid: String) {
        collection.deleteOne(Document("uuid", uuid))
    }

    override fun deleteAll() {
        return collection.find().forEach {
            delete(it.getString("uuid"))
        }
    }

    override fun update(t: Vehiculo) {
        collection.replaceOne(Document("uuid", t.uuid), t.ToDocument())
    }
}