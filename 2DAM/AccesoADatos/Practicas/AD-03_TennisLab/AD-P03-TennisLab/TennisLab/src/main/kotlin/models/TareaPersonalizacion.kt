package models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id
import org.litote.kmongo.newId

@Serializable
data class TareaPersonalizacion(
    @BsonId @Contextual
    val _id: String = newId<TareaPersonalizacion>().toString(),
    var rigidez: Double,
    var peso: Double,
    var balance: Double,
    var precio: Double,
    // He referenciado
    var pedido_id: String?,
) {

    override fun toString(): String {
        return "TareaPersonalizacion(id=$_id, " +
                "rigidez=$rigidez, " +
                "peso=$peso, " +
                "balance=$balance, " +
                "precio=$precio, " +
                "pedido_id='${pedido_id}')"
    }
}