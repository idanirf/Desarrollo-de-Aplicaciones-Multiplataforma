package es.drodriguez.com.tennislabspring.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference

@Document("tareasPersonalizacion")
data class TareaPersonalizacion(
    @Id
    val _id: ObjectId = ObjectId.get(),
    val rigidez: Double,
    val peso: Double,
    val balance: Double,
    val precio: Double,
    @DocumentReference
    val pedido_id: Pedido? = null
    ) {
    //To String
    override fun toString(): String {
        return "TareaPersonalizacion(_id='$_id', rigidez=$rigidez, peso=$peso, balance=$balance, precio=$precio, pedido_id=$pedido_id)"
    }
}