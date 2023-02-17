package es.drodriguez.com.tennislabspring.models

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("tareasEncordado")
data class TareaEncordado(
    @Id
    val _id: ObjectId = ObjectId.get(),
    val precio: Double,
    val tensionVertical: Double,
    val cordajeVertical: String,
    val tensionHorizontal: Double,
    val cordajeHorizontal: String,
    val nudos: NumeroNudos,
    val pedido_id: Pedido?=null,
) {

        enum class NumeroNudos(numeroNudos: String) {
            DOS("DOS"),
            CUATRO("CUATRO");

            companion object {
                fun from(numeroNudos: String): NumeroNudos {
                    return when (numeroNudos.uppercase()) {
                        "DOS" -> DOS
                        "CUATRO" -> CUATRO
                        else -> throw IllegalArgumentException("Nudos no válidos")
                    }
                }
            }
        }

        override fun toString(): String {
            return "TareaEncordado(id=$_id, " +
                    "precio=$precio, " +
                    "tensionVertical=$tensionVertical, " +
                    "cordajeVertical='$cordajeVertical', " +
                    "tensionHorizontal=$tensionHorizontal, " +
                    "cordajeHorizontal='$cordajeHorizontal', " +
                    "nudos=$nudos, " +
                    "pedido_id='${pedido_id}')"
        }

}