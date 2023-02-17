package es.drodriguez.com.tennislabspring.models

import es.drodriguez.com.tennislabspring.serializers.LocalDateTimeSerializer
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference
import java.time.LocalDateTime

@Document("pedidos")
data class Pedido(
    @Id
    val _id: ObjectId = ObjectId.get(),
    val estado: TipoEstado,
    @Serializable(with = LocalDateTimeSerializer::class)
    val fechaEntrada: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class)
    val fechaSalidaProgramada: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class)
    val fechaEntrega: LocalDateTime,
    val precio: Double,
    @DocumentReference
    val usuario_id: User?=null,
) {
    override fun toString(): String {
        return "Pedido(id=$_id, " +
                "estado=$estado, " +
                "fechaEntrada='$fechaEntrada', " +
                "fechaSalidaProgramada='$fechaSalidaProgramada', " +
                "fechaEntrega='$fechaEntrega', " +
                "precio=$precio, " +
                "usuario_id=${usuario_id})"
    }

    enum class TipoEstado(val estado: String) {
        ENTRADA("ENTRADA"),
        EN_PROCESO("EN_PROCESO"),
        TERMINADO("TERMINADO"),
        ENTREGADO("ENTREGADO");
        companion object {
            fun from(estado: String): TipoEstado {
                return when (estado.uppercase()) {
                    "ENTRADA" -> ENTRADA
                    "EN_PROCESO" -> EN_PROCESO
                    "TERMINADO" -> TERMINADO
                    "ENTREGADO" -> ENTREGADO
                    else -> throw IllegalArgumentException("Estado no válido.")
                }
            }
        }
    }
}