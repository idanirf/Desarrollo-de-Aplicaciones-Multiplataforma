package models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.newId
import kotlinx.serialization.json.Json
import serializers.LocalDateTimeSerializer
import java.time.LocalDateTime

/**
 * Pedido
 *
 * @property _id
 * @property estado
 * @property fechaEntrada
 * @property fechaSalidaProgramada
 * @property fechaEntrega
 * @property precio
 * @property usuario_id
 * @constructor Create empty Pedido
 */
@Serializable
data class Pedido(
    @BsonId @Contextual
    val _id: String = newId<Pedido>().toString(),
    val estado: TipoEstado,
    @Serializable(with = LocalDateTimeSerializer::class)
    val fechaEntrada: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class)
    val fechaSalidaProgramada: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class)
    val fechaEntrega: LocalDateTime,
    val precio: Double,
    //He referenciado a los usuarios en pedidos porque pienso
    // que puede tener que actualizarse bastante
    val usuario_id: String
    ){


    /**
     * Tipo estado: Función que parsea los enum de tipoEstado para el estado del pedido.
     * Que puede ser recibido, en proceso o terminado
     *
     * @property est
     * @constructor Create empty Tipo estado
     */
    enum class TipoEstado(val est: String) {
    RECIBIDO("RECIBIDO"),
    EN_PROCESO("EN PROCESO"),
    TERMINADO("TERMINADO");

    companion object {
        fun from(estado: String): TipoEstado {
            return when (estado.uppercase()) {
                "RECIBIDO" -> RECIBIDO
                "EN_PROCESO" -> EN_PROCESO
                "TERMINADO" -> TERMINADO
                else -> throw IllegalArgumentException("Estado no reconocido.")
            }
        }
    }
    }

    /**
     * To string: Método que sirve para convertir a string el objeto de pedido.
     *
     * @return Devuelve el string del pedido.
     */
    override fun toString(): String {
        return "Pedido(id=$_id, " +
                "estado=$estado, " +
                "fechaEntrada=${fechaEntrada.toString()}, " +
                "fechaSalidaProgramada=${fechaSalidaProgramada.toString()}, " +
                "fechaEntrega=${fechaEntrega.toString()}, " +
                "precio=$precio, " +
                "usuario_id=${usuario_id})"
    }

}