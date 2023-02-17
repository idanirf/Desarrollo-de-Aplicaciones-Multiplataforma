package es.drodriguez.com.tennislabspring.models

import es.drodriguez.com.tennislabspring.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference
import java.time.LocalDateTime

@Document("maquinasEncordar")
data class MaquinaEncordar(
    @Id
    val _id: ObjectId = ObjectId.get(),
    val marca: String,
    val modelo: String,
    @Serializable(with = LocalDateTimeSerializer::class)
    val fechaAdquisicion: LocalDateTime,
    val numeroSerie: String,
    val tipo: TipoEncordaje,
    val tensionMaxima: Double,
    val tensionMinima: Double,
    @DocumentReference
    val turno_id: Turno? = null
) {
    override fun toString(): String {
        return "MaquinaEncordar(id=$_id, " +
                "marca='$marca', " +
                "modelo='$modelo', " +
                "fechaAdquisicion=$fechaAdquisicion, " +
                "numeroSerie=$numeroSerie, " +
                "tipo=$tipo, " +
                "tensionMaxima=$tensionMaxima, " +
                "tensionMinima=$tensionMinima, " +
                "turno_id=${turno_id})"
    }

    enum class TipoEncordaje(val tipoEncordaje: String) {
        MANUAL("MANUAL"),
        AUTOMATICA("AUTOMATICA");
        companion object {
            fun from(encordaje: String): TipoEncordaje {
                return when (encordaje.uppercase()) {
                    "MANUAL" -> MANUAL
                    "AUTOMATICA" -> AUTOMATICA
                    else -> throw IllegalArgumentException("Tipo de encordaje no válido.")
                }
            }
        }
    }
}