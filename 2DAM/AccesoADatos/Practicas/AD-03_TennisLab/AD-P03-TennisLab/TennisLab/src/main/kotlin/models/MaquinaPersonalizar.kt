package models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id
import org.litote.kmongo.newId
import serializers.LocalDateTimeSerializer
import java.time.LocalDate
import java.time.LocalDateTime

@Serializable
data class MaquinaPersonalizar(
    @BsonId @Contextual
    val _id: String = newId<MaquinaPersonalizar>().toString(),
    val marca: String,
    val modelo: String,
    @Serializable(with = LocalDateTimeSerializer::class)
    val fechaAdquisicion: LocalDateTime,
    val numeroSerie: String,
    val swingweight: String,
    val balance: Double,
    val rigidez: Double,
    //He referenciado
    val turno_id: String? = null // A lo mejor no tiene ningun turno asociado
                                // ese dia)
){

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