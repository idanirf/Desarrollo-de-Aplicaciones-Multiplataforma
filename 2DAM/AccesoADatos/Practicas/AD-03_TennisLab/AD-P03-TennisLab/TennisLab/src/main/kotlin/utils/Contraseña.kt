package utils

import java.nio.charset.StandardCharsets
import com.google.common.hash.Hashing


object Contraseñas {
    /**
     * Encriptar: Método que sirve para encriptar una contraseña en sha512
     *
     * @param password
     * @return devuelve la clave encriptada en sha512
     */
    fun encriptar(password:String):String{
        return Hashing.sha512().hashString(password, StandardCharsets.UTF_8).toString()
    }
}