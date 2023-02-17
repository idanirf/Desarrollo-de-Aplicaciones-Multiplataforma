package es.drodriguez.com.tennislabspring.utils

import java.nio.charset.StandardCharsets
import com.google.common.hash.Hashing


object Contraseñas {
    fun encriptar(password:String):String{
        return Hashing.sha512().hashString(password, StandardCharsets.UTF_8).toString()
    }
}