package es.drodriguez.com.tennislabspring.dto


import dto.UserDto
import es.drodriguez.com.tennislabspring.models.Pedido
import kotlinx.serialization.Serializable


@Serializable
data class PedidoDto(
    val _id: String,
    val estado: String,
    val fechaEntrada: String,
    val fechaSalidaProgramada: String,
    val fechaEntrega: String,
    val precio: Double,
    val usuario_id: String,
)



fun pedidoToDto(pedido: Pedido): PedidoDto {
    return PedidoDto(
        _id = pedido._id.toString(),
        estado = pedido.estado.toString(),
        fechaEntrada = pedido.fechaEntrada.toString(),
        fechaSalidaProgramada = pedido.fechaSalidaProgramada.toString(),
        fechaEntrega = pedido.fechaEntrega.toString(),
        precio = pedido.precio,
        usuario_id = pedido.usuario_id.toString(),
    )
}