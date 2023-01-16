import de.nycode.bcrypt.hash
import de.nycode.bcrypt.verify

fun main() {
    bcryptCorrecto()
    bcryptIncorrecto()
    cifraBcrypt()
}

fun bcryptCorrecto() {
    val hash = hash("Dani Rodríguez")
    if (verify("Dani Rodríguez", hash)) {
        println("Correcto")
    } else {
        println("Incorrecto")
    }
}

fun bcryptIncorrecto() {
    val hash = hash("Dani Rodríguez")
    if (verify("Danie Rodríguez", hash)) {
        println("Correcto")
    } else {
        println("Incorrecto")
    }
}

fun cifraBcrypt() {
    val contasena = "aprueboPSP"
    val salt = 12
    val conviertoContrasenaHash = hash(contasena, salt)
    println("La contraseña original es: $contasena")
    print("La contraseña cifrada es: ")
    conviertoContrasenaHash.forEach { print(it) }
    println()
    val comprobar: Boolean = verify(contasena, conviertoContrasenaHash)
    println(comprobar)
}

