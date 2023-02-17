package es.drodriguez.com.tennislabspring.models

import es.drodriguez.com.tennislabspring.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference
import java.time.LocalDateTime

@Document("maquinasPersonalizar")
data class MaquinaPersonalizar(
    @Id
    val _id: ObjectId = ObjectId.get(),
    val marca: String,
    val modelo: String,
    @Serializable(with = LocalDateTimeSerializer::class)
    val fechaAdquisicion: LocalDateTime,
    val numeroSerie: String,
    val swingweight: String,
    val balance: Double,
    val rigidez: Double,
    @DocumentReference
    val turno_id: Turno? = null
) {
    override fun toString(): String {
        return "MaquinaPersonalizar(id=$_id, " +
                "marca='$marca', " +
                "modelo='$modelo', " +
                "fechaAdquisicion=$fechaAdquisicion, " +
                "numeroSerie=$numeroSerie, " +
                "swingweight=$swingweight, " +
                "balance=$balance, " +
                "rigidez=$rigidez, " +
                "turno_id=${turno_id})"
    }

}