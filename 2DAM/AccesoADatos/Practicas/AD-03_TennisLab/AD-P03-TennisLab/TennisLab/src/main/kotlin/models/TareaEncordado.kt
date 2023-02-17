package models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id
import org.litote.kmongo.newId

@Serializable
data class TareaEncordado(
    @BsonId @Contextual
    val _id: String = newId<TareaEncordado>().toString(),
    val precio: Double,
    val tensionVertical: Double,
    val cordajeVertical: String,
    val tensionHorizontal: Double,
    val cordajeHorizontal: String,
    val nudos: NumeroNudos,
    val pedido_id: String?,

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