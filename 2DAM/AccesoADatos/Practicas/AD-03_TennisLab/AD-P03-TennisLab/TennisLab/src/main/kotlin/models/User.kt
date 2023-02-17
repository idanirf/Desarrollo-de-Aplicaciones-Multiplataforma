package models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.newId

@Serializable
data class User(
        @BsonId @Contextual
        val _id: String = newId<User>().toString(),
        val id: String,
        val name: String,
        val username: String,
        val email: String,
        val password: String,
        val tipoUser: TipoUsuario,
        val phone: String,
        val website: String,
    ){

    /**
     * Tipo usuario: Enum que sirve para elegir el tipo de usuario.
     *
     * @constructor
     *
     * @param tipoUser
     */
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
}