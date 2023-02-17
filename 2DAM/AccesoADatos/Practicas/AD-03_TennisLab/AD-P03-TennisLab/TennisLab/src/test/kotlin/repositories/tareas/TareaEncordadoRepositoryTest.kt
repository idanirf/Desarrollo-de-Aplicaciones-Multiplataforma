package repositories.tareas

import com.mongodb.assertions.Assertions
import com.mongodb.assertions.Assertions.assertNotNull
import data.Data
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import models.Pedido
import models.TareaEncordado
import models.User
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import repositories.mongo.PedidoRepository
import repositories.mongo.TareaEncordadoRepository
import repositories.mongo.UserRepository
import services.mongo.MongoDbManager
import java.time.LocalDateTime
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TareaEncordadoRepositoryTest {
    private val tareaEncordadoRepository = TareaEncordadoRepository()
    private val userRepository = UserRepository()
    private val pedidoRepository = PedidoRepository()

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

    val pedido = Pedido(
        _id = "123",
        estado = Pedido.TipoEstado.EN_PROCESO,
        fechaEntrada = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
        fechaSalidaProgramada = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
        fechaEntrega = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
        precio = 222.2,
        usuario_id = "21",
    )

    val tarea = TareaEncordado(
        _id = "444",
        precio = 8.00,
        tensionVertical = 1.20,
        cordajeVertical = "Cordaje Vertical",
        tensionHorizontal = 1.10,
        cordajeHorizontal = "Cordaje Horizontal",
        nudos = TareaEncordado.NumeroNudos.DOS,
        pedido_id = "123",
    )

    @Test
    fun findAll() = runBlocking {
        MongoDbManager.database.getCollection<TareaEncordado>().drop()
        userRepository.save(user)
        pedidoRepository.save(pedido)
        tareaEncordadoRepository.save(tarea)
        val list = tareaEncordadoRepository.findAll().toList()
        assertAll(
            { assertNotNull(list) },
            { assertEquals(1, list.size) }
        )
        userRepository.delete(user._id)
        pedidoRepository.delete(pedido._id)
        tareaEncordadoRepository.delete(tarea._id)
    }

    @Test
    fun findById() = runBlocking {
        userRepository.save(user)
        pedidoRepository.save(pedido)
        tareaEncordadoRepository.save(tarea)
        val tareaEncordado = tareaEncordadoRepository.findById("444")
        assertAll(
            { assertNotNull(tareaEncordado) },
            { assertEquals("444", tareaEncordado?._id) }
        )
        userRepository.delete(user._id)
        pedidoRepository.delete(pedido._id)
        tareaEncordadoRepository.delete(tarea._id)
    }

    @Test
    fun save() = runBlocking {
        userRepository.save(user)
        pedidoRepository.save(pedido)
        val tareaEncordado = tareaEncordadoRepository.save(tarea)
        assertAll(
            { assertNotNull(tareaEncordado) },
            { assertEquals("444", tarea._id) }
        )
        userRepository.delete(user._id)
        pedidoRepository.delete(pedido._id)
        tareaEncordadoRepository.delete(tarea._id)
    }

    @Test
    fun update() = runBlocking {
        userRepository.save(user)
        pedidoRepository.save(pedido)
        tareaEncordadoRepository.save(tarea)
        val tareaEncordado = tareaEncordadoRepository.update(tarea)
        assertAll(
            { assertNotNull(tareaEncordado) },
            { assertEquals("444", tarea._id) }
        )
        userRepository.delete(user._id)
        pedidoRepository.delete(pedido._id)
        tareaEncordadoRepository.delete(tarea._id)
    }

    @Test
    fun delete() = runBlocking {
        userRepository.save(user)
        pedidoRepository.save(pedido)
        tareaEncordadoRepository.save(tarea)

        val tareaEncordado = tareaEncordadoRepository.delete(tarea._id)
        assertAll(
            { assertNotNull(tareaEncordado) },
            { assertEquals("444", tarea._id) }
        )
        userRepository.delete(user._id)
        pedidoRepository.delete(pedido._id)
        tareaEncordadoRepository.delete(tarea._id)
    }
}