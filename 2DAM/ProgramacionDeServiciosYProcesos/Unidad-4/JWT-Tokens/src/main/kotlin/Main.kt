import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

fun main() {
    println("Generación de Tokens con JWT")

    // 1. Crear cifrado con JWT
    val algoritmoJWT: Algorithm = Algorithm.HMAC256("AlgoritmoSecreto")

    // 2. Crear token
    val jToken: String = JWT.create()
        .withIssuer("IES Luis Vives")
        .withSubject("Daniel Rodriguez Fernandez")
        .withClaim("NIA", "1234")
        .withClaim("email", "daniel.rodriguezfernandez@alumno.iesluisvives.org")
        .withIssuedAt(Date())
        .withExpiresAt(Date(System.currentTimeMillis() + 5000))
        .sign(algoritmoJWT)

    //3. Imprimir Token cifrado
    println(jToken)

    //4. Decodificar Token
    try {
        val verify = JWT.require(algoritmoJWT).build()
        val decodificado = verify.verify(jToken)

        // 5. Imprimir datos del Token ya decodificado
        println(decodificado.getClaim("NIA").asString())
        println(decodificado.getClaim("email").asString())
    } catch (e: Exception) {
        System.err.println("Error: ${e.message}")
    }
}