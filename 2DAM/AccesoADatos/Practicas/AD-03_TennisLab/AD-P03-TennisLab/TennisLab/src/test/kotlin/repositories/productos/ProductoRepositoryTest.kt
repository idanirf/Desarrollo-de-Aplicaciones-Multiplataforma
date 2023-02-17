package repositories.productos

import com.mongodb.assertions.Assertions
import data.Data
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import models.Pedido
import models.Producto
import models.User
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertAll
import repositories.mongo.PedidoRepository
import repositories.mongo.ProductoRepository
import repositories.mongo.TareaEncordadoRepository
import repositories.mongo.UserRepository
import services.mongo.MongoDbManager
import utils.Contraseñas
import java.time.LocalDateTime
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductoRepositoryTest {
    private val productoRepository = ProductoRepository()
    private val userRepository = UserRepository()
    private val pedidoRepository = PedidoRepository()

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

    val pedido = Pedido(
        _id = "123",
        estado = Pedido.TipoEstado.EN_PROCESO,
        fechaEntrada = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
        fechaSalidaProgramada = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
        fechaEntrega = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
        precio = 222.2,
        usuario_id = "34",
    )
    val producto = Producto(
        _id = "255",
        marca = "Raqueta Tenis 12",
        modelo = "Tennis 12",
        precio = 16.00,
        stock = "12",
        tipoProducto = Producto.TipoProducto.RAQUETA,
        pedido_id = "123"
    )

    @Test
    fun findAll() = runBlocking {
        MongoDbManager.database.getCollection<Producto>().drop()
        userRepository.save(user)
        pedidoRepository.save(pedido)
        productoRepository.save(producto)
        val productos = productoRepository.findAll().toList()
        assertAll(
            { Assertions.assertNotNull(productos) },
            { assertEquals(1, productos.size) }
        )
        userRepository.delete(user._id)
        pedidoRepository.delete(pedido._id)
        productoRepository.delete(producto._id)
    }

    @Test
    fun findById() = runBlocking {
        userRepository.save(user)
        pedidoRepository.save(pedido)
        val productos = productoRepository.findById(producto._id)
        assertAll(
            { Assertions.assertNotNull(producto) },
            { assertEquals("255", producto._id) }
        )
        userRepository.delete(user._id)
        pedidoRepository.delete(pedido._id)
        productoRepository.delete(producto._id)
    }

    @Test
    fun save() = runBlocking {
        userRepository.save(user)
        pedidoRepository.save(pedido)
        val productoIns = productoRepository.save(producto)
            assertAll(
                { Assertions.assertNotNull(productoIns) },
                { assertEquals("255", producto._id) }
        )
        userRepository.delete(user._id)
        pedidoRepository.delete(pedido._id)
        productoRepository.delete(producto._id)
    }

    @Test
    fun update() = runBlocking {
        userRepository.save(user)
        pedidoRepository.save(pedido)
        val pedidoUpd = pedidoRepository.update(pedido)
        assertAll(
            { Assertions.assertNotNull(pedidoUpd) },
            { assertEquals("255", producto._id) }
        )
        userRepository.delete(user._id)
        pedidoRepository.delete(pedido._id)
        productoRepository.delete(producto._id)

    }

    @Test
    fun delete(): Unit = runBlocking {
        val productoDel = productoRepository.findById("63d3893966730d6a67044413")
        productoDel?.let {
            productoRepository.delete(it._id)
            productoRepository.delete(productoDel._id)
            val productoDel = productoRepository.findById("63d3893966730d6a67044413")
            assertAll(
                { Assertions.assertNotNull(productoDel) },
                { assertEquals(null, productoDel) },
            )
        }
    }

}