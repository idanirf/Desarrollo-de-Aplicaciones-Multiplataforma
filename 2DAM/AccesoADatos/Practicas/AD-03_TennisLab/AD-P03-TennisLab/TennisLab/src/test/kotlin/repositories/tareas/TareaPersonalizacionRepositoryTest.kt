package repositories.tareas

import com.mongodb.assertions.Assertions
import data.Data
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import models.Pedido
import models.TareaPersonalizacion
import models.User
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import repositories.mongo.PedidoRepository
import repositories.mongo.TareaPersonalizacionRepository
import repositories.mongo.UserRepository
import services.mongo.MongoDbManager
import java.time.LocalDateTime
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TareaPersonalizacionRepositoryTest {
    private val tareaPersonalizacionRepository = TareaPersonalizacionRepository()
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

    val tarea = TareaPersonalizacion(
        _id = "666",
        rigidez = 8.00,
        peso = 1.20,
        balance = 1.10,
        precio = 222.2,
        pedido_id = "123",
    )


    @Test
    fun findAll() = runBlocking {
        MongoDbManager.database.getCollection<TareaPersonalizacion>().drop()
        userRepository.save(user)
        pedidoRepository.save(pedido)
        tareaPersonalizacionRepository.save(tarea)
        val list = tareaPersonalizacionRepository.findAll().toList()
        assertAll(
            { Assertions.assertNotNull(list) },
            { assertEquals(1, list.size) }
        )
        userRepository.delete(user._id)
        pedidoRepository.delete(pedido._id)
        tareaPersonalizacionRepository.delete(tarea._id)
    }

    @Test
    fun findById() = runBlocking {
        userRepository.save(user)
        pedidoRepository.save(pedido)
        tareaPersonalizacionRepository.save(tarea)
        val tareas = tareaPersonalizacionRepository.findById("666")
        assertAll(
            { Assertions.assertNotNull(tarea) },
            { assertEquals("666", tarea._id) }
        )
        userRepository.delete(user._id)
        pedidoRepository.delete(pedido._id)
        tareaPersonalizacionRepository.delete(tarea._id)
    }

    @Test
    fun create() = runBlocking {
        userRepository.save(user)
        pedidoRepository.save(pedido)
        val tareaIns = tareaPersonalizacionRepository.save(tarea)
        assertAll(
            { Assertions.assertNotNull(tarea) },
            { assertEquals("666", tarea._id) }
        )
        userRepository.delete(user._id)
        pedidoRepository.delete(pedido._id)
        tareaPersonalizacionRepository.delete(tarea._id)
    }

    @Test
    fun update() = runBlocking {
        val tareaUp = tareaPersonalizacionRepository.findById("666")
        tarea?.let {
            it.rigidez = 9.00
            it.peso = 1.30
            it.balance = 1.20
            it.precio = 333.3
            it.pedido_id = "63d3893966730d6a67044413"
            val tareaUpd = tareaPersonalizacionRepository.update(it)
            assertAll(
                { Assertions.assertNotNull(tareaUpd) },
                { assertEquals(9.00, tareaUp?.rigidez) },
                { assertEquals(1.30, tareaUp?.peso) },
                { assertEquals(1.20, tareaUp?.balance) },
                { assertEquals(333.3, tareaUp?.precio) },
                { assertEquals("63d3893966730d6a67044413", tareaUp?.pedido_id) }
            )
        }
    }

    @Test
    fun delete(): Unit = runBlocking {
        val tareaDel = tareaPersonalizacionRepository.findById("666")
        tareaDel?.let {
            tareaPersonalizacionRepository.delete(it._id)
            tareaPersonalizacionRepository.delete(tarea._id)
            val tareaDel2 = tareaPersonalizacionRepository.findById("666")
            assertAll(
                { Assertions.assertNotNull(tareaDel2) },
                { assertEquals(null, tareaDel2) }
            )
        }
    }


}