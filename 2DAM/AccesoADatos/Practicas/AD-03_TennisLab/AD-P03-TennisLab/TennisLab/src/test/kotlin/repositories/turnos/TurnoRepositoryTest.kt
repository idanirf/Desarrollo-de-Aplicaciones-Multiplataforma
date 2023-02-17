package repositories.turnos

import com.mongodb.assertions.Assertions.assertNotNull
import data.Data
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import models.Turno
import models.User
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.litote.kmongo.MongoOperator
import org.litote.kmongo.util.idValue
import repositories.mongo.TurnoRepository
import repositories.mongo.UserRepository
import services.mongo.MongoDbManager
import java.time.LocalDateTime
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TurnoRepositoryTest {
    val turnoRepository = TurnoRepository()
    val userRepository = UserRepository()

    val user = User(
        _id = "21",
        id = "55",
        name = "Jorge",
        username = "Juan",
        email = "juan@gmail.com",
        password = "hola",
        tipoUser = User.TipoUsuario.ADMIN,
        phone = "736363736",
        website = "http://www.juan"
    )
    val turno = Turno(
        _id = "21",
        fechaInicio = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
        fechaFinal = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
        usuario_id = "21"
    )


    //Test del findAll de turnos
    @Test
    fun findAll() = runBlocking {
        MongoDbManager.database.getCollection<Turno>().drop()
        userRepository.save(user)
        turnoRepository.save(turno)
        val list = turnoRepository.findAll().toList()
        assertAll(
            { assertNotNull(list) },
            { assertEquals(1, list.size) }
        )
        userRepository.delete(user._id)
        turnoRepository.delete(turno._id)
    }

    //Test del findById de turnos
    @Test
    fun findById() = runBlocking {
        userRepository.save(user)
        turnoRepository.save(turno)
        val turnos = turnoRepository.findById("21")
        assertAll(
            { assertNotNull(turno) },
            { assertEquals("21", turno._id) },

        )
        userRepository.delete(user._id)
        turnoRepository.delete(turno._id)
    }

    //Test del insert de turnos
    @Test
    fun insert() = runBlocking {
        userRepository.save(user)
        val resul = turnoRepository.save(turno)
        Assertions.assertEquals("21", turno._id)
    }

    //Test del update de turnos
    @Test
    fun update() = runBlocking {
        userRepository.save(user)
        turnoRepository.save(turno)
        val turno = Turno(
            _id = "21",
            fechaInicio = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
            fechaFinal = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
            usuario_id = "55"
        )
        val resul = turnoRepository.update(turno)
        Assertions.assertEquals("21", turno._id)
        userRepository.delete(user._id)
        turnoRepository.delete(turno._id)
    }

    //Test del delete de turnos
    @Test
    fun delete() = runBlocking {
        val resul = turnoRepository.save(turno)
        val resulDel = turnoRepository.delete(turno._id)
        assertAll(
            { assertEquals(resulDel?.idValue,  resul.idValue) }
        )
    }
}