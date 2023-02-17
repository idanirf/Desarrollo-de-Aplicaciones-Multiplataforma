package repositories.maquinas

import com.mongodb.assertions.Assertions
import data.Data
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import models.MaquinaEncordar
import models.Turno
import models.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import repositories.mongo.MaquinaEncordarRepository
import repositories.mongo.TurnoRepository
import repositories.mongo.UserRepository
import services.mongo.MongoDbManager
import utils.Contraseñas
import java.time.LocalDate
import java.time.LocalDateTime

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MaquinaEcordadoRepositoryTest {
    val maquinasEcordadoRepository = MaquinaEncordarRepository()
    val userRepository = UserRepository()
    val turnoRepository = TurnoRepository()

    val maquina = MaquinaEncordar(
        _id = "255",
        marca = "Marca 1",
        modelo = "Modelo 1 Test",
        fechaAdquisicion = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
        numeroSerie = "63d2e7f1941f2554de9bc4c0",
        tipo = MaquinaEncordar.TipoEncordaje.MANUAL,
        tensionMaxima = 100.0,
        tensionMinima = 100.0,
        turno_id = "22"
    )

    val user =  User(
        _id = "34",
        id = "55",
        name = "Daniel",
        username = "DanielRodriguez",
        email = "cliente@gmail.com",
        password = Contraseñas.encriptar("cliente"),
        tipoUser = User.TipoUsuario.CLIENTE,
        phone = "736363736",
        website = "http://www.juan")

    val turno = Turno(
        _id = "22",
        fechaInicio = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
        fechaFinal = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
        usuario_id = "34",
    )


    @Test
    fun findAll() = runBlocking {
        MongoDbManager.database.getCollection<MaquinaEncordar>().drop()
        userRepository.save(user)
        turnoRepository.save(turno)
        maquinasEcordadoRepository.save(maquina)
        val maquinas = maquinasEcordadoRepository.findAll().toList()

        assertAll(
            { Assertions.assertNotNull(maquinas) },
            { assertEquals(1, maquinas.size) }
        )

        userRepository.delete(user._id)
        turnoRepository.delete(turno._id)
        maquinasEcordadoRepository.delete(maquina._id)
    }

    @Test
    fun findById() = runBlocking {
        userRepository.save(user)
        turnoRepository.save(turno)
        maquinasEcordadoRepository.save(maquina)
        val maquinas = maquinasEcordadoRepository.findById("255")
        assertAll(
            { Assertions.assertNotNull(maquinas) },
            { assertEquals("255", maquinas?._id) }
        )
        userRepository.delete(user._id)
        turnoRepository.delete(turno._id)
        maquinasEcordadoRepository.delete(maquina._id)
    }

    @Test
    fun create() = runBlocking {
        val maquinas = maquinasEcordadoRepository.save(maquina)
        assertAll(
            { Assertions.assertNotNull(maquinas) },
            { assertEquals("255", maquina._id) }
        )
    }

    @Test
    fun update() = runBlocking {
        val maquinas = maquinasEcordadoRepository.update(maquina)
        assertAll(
            { Assertions.assertNotNull(maquinas) },
            { assertEquals("255", maquina._id) }
        )
    }

    @Test
    fun delete() = runBlocking {
        val maquinas = maquinasEcordadoRepository.delete(maquina._id)
        assertAll(
            { Assertions.assertNotNull(maquinas) },
            { assertEquals("255", maquina._id) }
        )
    }
}