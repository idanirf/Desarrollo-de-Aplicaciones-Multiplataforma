package es.drodriguez.com.tennislabspring.dto

import es.drodriguez.com.tennislabspring.models.Producto
import kotlinx.serialization.Serializable


@Serializable
data class ProductoDto(
    val _id: String,
    var marca: String,
    var modelo: String,
    var precio: Double,
    var stock: String,
    var tipoProducto: String,
    var pedido_id: String
)

fun productoToDto(producto: Producto): ProductoDto {
    return ProductoDto(
        _id = producto._id.toString(),
        marca = producto.marca,
        modelo = producto.modelo,
        precio = producto.precio,
        stock = producto.stock,
        tipoProducto = producto.tipoProducto.toString(),
        pedido_id = producto.pedido_id.toString(),
    )
}