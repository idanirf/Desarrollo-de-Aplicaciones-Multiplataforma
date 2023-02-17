package es.drodriguez.com.tennislabspring.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference

@Document("productos")
data class Producto(
    @Id
    val _id: ObjectId = ObjectId.get(),
    var marca: String,
    var modelo: String,
    var precio: Double,
    var stock: String,
    var tipoProducto: TipoProducto,
    @DocumentReference
    var pedido_id: Pedido?=null,
) {
    override fun toString(): String {
        return "Producto(id=$_id, " +
                "marca='$marca', " +
                "modelo='$modelo', " +
                "precio=$precio, " +
                "stock=$stock, " +
                "tipoProducto=$tipoProducto, " +
                "pedido=${pedido_id})"
    }

    enum class TipoProducto(val tipoProducto: String) {
        RAQUETA("RAQUETA"),
        CORDAJE("CORDAJE"),
        COMPLEMENTO("COMPLEMENTO");
        companion object {
            fun from(producto: String): TipoProducto {
                return when (producto.uppercase()) {
                    "RAQUETA" -> RAQUETA
                    "CORDAJE" -> CORDAJE
                    "COMPLEMENTO" -> COMPLEMENTO
                    else -> throw IllegalArgumentException("Producto no válido.")
                }
            }
        }
    }
}