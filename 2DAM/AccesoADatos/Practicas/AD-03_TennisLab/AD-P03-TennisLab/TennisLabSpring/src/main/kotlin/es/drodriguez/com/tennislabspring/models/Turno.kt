package es.drodriguez.com.tennislabspring.models

import es.drodriguez.com.tennislabspring.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference
import java.time.LocalDateTime

@Document("turnos")
data class Turno(
    @Id
    val _id: ObjectId = ObjectId.get(),
    @Serializable(with = LocalDateTimeSerializer::class)
    val fechaInicio: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class)
    val fechaFinal: LocalDateTime,
    @DocumentReference
    val usuario_id : User? = null
) {
    //To String
    override fun toString(): String {
        return "Turno(_id='$_id', fechaInicio='$fechaInicio', fechaFinal='$fechaFinal', usuario_id=$usuario_id)"
    }
}