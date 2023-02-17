package repositories.pedidos

import com.mongodb.assertions.Assertions
import data.Data
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import models.Pedido
import models.User
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import repositories.mongo.PedidoRepository
import repositories.mongo.UserRepository
import services.mongo.MongoDbManager
import utils.Contraseñas
import java.time.LocalDateTime
import kotlin.test.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PedidoRepositoryTest {
    private val pedidoRepository = PedidoRepository()
    val userRepository = UserRepository()

    val user = User(
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

    val pedido = Pedido(
        _id = "123",
        estado = Pedido.TipoEstado.EN_PROCESO,
        fechaEntrada = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
        fechaSalidaProgramada = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
        fechaEntrega = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
        precio = 222.2,
        usuario_id = "34",
    )

    @Test
    fun findAll() = runBlocking {
        MongoDbManager.database.getCollection<Pedido>().drop()
        userRepository.save(user)
        pedidoRepository.save(pedido)
        val pedidos = pedidoRepository.findAll().toList()
        assertAll(
            { Assertions.assertNotNull(pedidos) },
            { org.junit.jupiter.api.Assertions.assertEquals(1, pedidos.size) }
        )
        userRepository.delete(user._id)
        pedidoRepository.delete(pedido._id)
    }

    @Test
    fun findById() = runBlocking {
        userRepository.save(user)
        pedidoRepository.save(pedido)
        val pedidos = pedidoRepository.findById("123")
        assertAll(
            { Assertions.assertNotNull(pedidos) },
            { assertEquals("123", pedidos?._id) }
        )
        userRepository.delete(user._id)
        pedidoRepository.delete(pedido._id)
    }

    @Test
    fun create() = runBlocking {
        userRepository.save(user)
        val pedidoIns = pedidoRepository.save(pedido)
        assertAll(
            { Assertions.assertNotNull(pedido) },
            { assertEquals("123", pedido._id) }

        )
        userRepository.delete(user._id)
        pedidoRepository.delete(pedido._id)

    }

    @Test
    fun delete() = runBlocking {
        userRepository.save(user)
        pedidoRepository.save(pedido)
        val pedidoo = pedidoRepository.findById(pedido._id)
        pedidoRepository.delete(pedido._id)
        userRepository.delete(user._id)
        val pedidooo = pedidoRepository.findById(pedido._id)

        assertAll(
            { assertNotNull(pedidoo) },
            { assertNull(pedidooo) }
        )

    }

        @Test
        fun update() = runBlocking {
            userRepository.save(user)
            pedidoRepository.save(pedido)
            val pedidoUpd = pedidoRepository.update(pedido)
            assertAll(
                { Assertions.assertNotNull(pedidoUpd) },
                { assertEquals("123", pedido._id) }
            )
            userRepository.delete(user._id)
            pedidoRepository.delete(pedido._id)
        }
    }

