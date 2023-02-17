package models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.newId
import serializers.LocalDateTimeSerializer
import java.time.LocalDate
import java.time.LocalDateTime

@Serializable
data class Turno(
    @BsonId @Contextual
    val _id: String = newId<Turno>().toString(),
    @Serializable(with = LocalDateTimeSerializer::class)
    val fechaInicio: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class)
    val fechaFinal: LocalDateTime,
    // He referenciado los usuarios en los turnos debido que si debemos actualizarlos
    // será mucho mas faciles y si lo embebemos las actualizaciones seran mas dificiles
    // y pienso que los turnos es algo que se suele actualizar mucho
    val usuario_id : String
    ) {

    override fun toString(): String {
        return "Turno(_id=$_id, " +
                "fechaInicio=${fechaInicio.toString()}, " +
                "fechaFinal=${fechaFinal.toString()}, " +
                "usuario_id=${usuario_id})"
    }
}