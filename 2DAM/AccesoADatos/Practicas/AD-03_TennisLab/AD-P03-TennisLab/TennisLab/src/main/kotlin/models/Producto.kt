package models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id
import org.litote.kmongo.newId

@Serializable
data class Producto(
    @BsonId @Contextual
    val _id: String = newId<Producto>().toString(),
    var marca: String,
    var modelo: String,
    var precio: Double,
    var stock: String,
    var tipoProducto: TipoProducto,
    var pedido_id: String
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


    /**
     * Tipo producto: Enum que sirve para seleccionar el tipo de producto.
     *
     * @property tipoProducto
     */
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