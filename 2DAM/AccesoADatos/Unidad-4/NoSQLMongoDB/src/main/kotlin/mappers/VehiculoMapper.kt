package mappers

import models.Vehiculo
import org.bson.Document

fun Vehiculo.ToDocument(): Document {
    var dc = Document("uuid", this.uuid)
        .append("marca", this.marca)
        .append("modelo", this.modelo)
        .append("matricula", this.matricula)
        .append("uuidPersona", this.uuidPersona)
    return dc
}

fun Document.toVehiculo(): Vehiculo? {
    var uuid = this.getString("uuid")
    var marca = this.getString("marca")
    var modelo = this.getString("modelo")
    var matricula = this.getString("matricula")
    var uuidPersona = this.getString("uuidPersona")

    if (uuid == null || marca == null || modelo == null || matricula ==null){
        return null
    }
    return Vehiculo(uuid, marca, modelo, matricula, uuidPersona)
}