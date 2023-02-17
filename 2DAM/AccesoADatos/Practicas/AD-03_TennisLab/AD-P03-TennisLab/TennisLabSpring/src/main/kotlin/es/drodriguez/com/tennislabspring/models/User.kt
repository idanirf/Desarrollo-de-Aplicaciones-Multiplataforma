package es.drodriguez.com.tennislabspring.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("users")
data class User(
    @Id
    val _id: ObjectId = ObjectId.get(),
    val id: String,
    val name: String,
    val username: String,
    val email: String,
    val password: String,
    val tipoUser: TipoUsuario,
    val phone: String,
    val website: String,
) {
    //To String

    enum class TipoUsuario(tipoUser: String) {
        CLIENTE("CLIENTE"),
        TRABAJADOR("TRABAJADOR"),
        ADMIN("ADMIN");

        companion object {
            fun from(tipoUser: String): TipoUsuario {
                return when (tipoUser.uppercase()) {
                    "CLIENTE" -> CLIENTE
                    "TRABAJADOR" -> TRABAJADOR
                    "ADMIN" -> ADMIN
                    else -> throw IllegalArgumentException("Tipo de usuario no válido")
                }
            }
        }
    }

    override fun toString(): String {
        return "User(_id='$_id', id='$id', name='$name', username='$username', email='$email', password='$password', tipoUser=$tipoUser, phone='$phone', website='$website')"
    }
}
