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
data class MaquinaEncordar(
    @BsonId @Contextual
    val _id: String = newId<MaquinaEncordar>().toString(),
    val marca: String,
    val modelo: String,
    @Serializable(with = LocalDateTimeSerializer::class)
    val fechaAdquisicion: LocalDateTime,
    val numeroSerie: String,
    val tipo: TipoEncordaje,
    val tensionMaxima: Double,
    val tensionMinima: Double,
    //He referenciado
    val turno_id: String? = null // A lo mejor no tiene ningun turno asociado
                                // ese dia
) {

    /**
     * Tipo encordaje: Enum que sirve para seleccionar el tipo de encordaje de la máquina de encordar.
     *
     * @constructor
     *
     * @param valor
     */
    enum class TipoEncordaje(valor: String) {
        AUTOMATICA("AUTOMATICA"),
        MANUAL("MANUAL");

        companion object {
            fun from(tipoEncordaje: String): TipoEncordaje {
                return when (tipoEncordaje.uppercase()) {
                    "MANUAL" -> MANUAL
                    "AUTOMATICA" -> AUTOMATICA
                    else -> throw IllegalArgumentException("TipoEncordaje no reconocido")
                }
            }
        }
    }

    override fun toString(): String {
        return "MaquinaEncordar(id=$_id, " +
                "marca='$marca', " +
                "modelo='$modelo', " +
                "fechaAdquisicion=$fechaAdquisicion, " +
                "numeroSerie=$numeroSerie, " +
                "tipo=$tipo, " +
                "tensionMaxima=$tensionMaxima, " +
                "tensionMinima=$tensionMinima, " +
                "turno_id='${turno_id}')"
    }


}