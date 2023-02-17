package repositories.maquinas

import com.mongodb.assertions.Assertions
import data.Data
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import models.MaquinaEncordar
import models.MaquinaPersonalizar
import models.Turno
import models.User
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import repositories.mongo.MaquinaPersonalizarRepository
import repositories.mongo.TurnoRepository
import repositories.mongo.UserRepository
import services.mongo.MongoDbManager
import utils.Contraseñas
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MaquinaPersonalizacionRepositoryTest {
    val maquinasPersonalizacionRepository = MaquinaPersonalizarRepository()
    val userRepository = UserRepository()
    val turnoRepository = TurnoRepository()

    val maquina = MaquinaPersonalizar(
        _id = "255",
        marca = "Marca 1",
        modelo = "Modelo 1 Test",
        fechaAdquisicion = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
        numeroSerie = "63d2e7f1941f2554de9bc4c0",
        swingweight = "yes",
        balance = 100.0,
        rigidez = 100.0,
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
        website = "http://www.juan",
    )
    val turno = Turno(
        _id = "22",
        fechaInicio = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
        fechaFinal = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
        usuario_id = "34",
    )

    @Test
    fun findAll() = runBlocking {
        MongoDbManager.database.getCollection<MaquinaPersonalizar>().drop()

        userRepository.save(user)
        turnoRepository.save(turno)
        maquinasPersonalizacionRepository.save(maquina)
        val maquinas = maquinasPersonalizacionRepository.findAll().toList()

        assertAll(
            { Assertions.assertNotNull(maquinas) },
            { org.junit.jupiter.api.Assertions.assertEquals(1, maquinas.size) }
        )

        userRepository.delete(user._id)
        turnoRepository.delete(turno._id)
        maquinasPersonalizacionRepository.delete(maquina._id)
    }

    @Test
    fun findById() = runBlocking {
        userRepository.save(user)
        turnoRepository.save(turno)
        maquinasPersonalizacionRepository.save(maquina)
        val maquinas = maquinasPersonalizacionRepository.findById("255")
        assertAll(
            { Assertions.assertNotNull(maquinas) },
            { org.junit.jupiter.api.Assertions.assertEquals("255", maquinas?._id) }
        )
        userRepository.delete(user._id)
        turnoRepository.delete(turno._id)
        maquinasPersonalizacionRepository.delete(maquina._id)
    }

    @Test
    fun save() = runBlocking {
        val maquinaIns = maquinasPersonalizacionRepository.save(maquina)
        assertAll(
            { Assertions.assertNotNull(maquina) },
            { assertEquals("255", maquina._id) }
        )
    }

    @Test
    fun update() = runBlocking {
        val maquinaUpd = maquinasPersonalizacionRepository.update(maquina)
        assertAll(
            { Assertions.assertNotNull(maquina) },
            { assertEquals("255", maquina._id) }
        )
    }

    @Test
    fun delete() = runBlocking {
        val maquinaDel = maquinasPersonalizacionRepository.delete(maquina._id)
        assertAll(
            { Assertions.assertNotNull(maquina) },
            { assertEquals("255", maquina._id) }
        )
    }
}