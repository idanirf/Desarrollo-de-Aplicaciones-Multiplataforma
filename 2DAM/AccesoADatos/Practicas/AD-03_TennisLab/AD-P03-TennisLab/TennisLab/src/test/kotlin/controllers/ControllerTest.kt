package controllers


import data.Data
import kotlinx.coroutines.flow.toList
import org.junit.jupiter.api.Test
import kotlinx.coroutines.test.runTest
import models.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestInstance
import repositories.cache.UserRepositoryCache
import repositories.ktorfit.KtorfitRepository
import repositories.mongo.*
import services.cache.CacheClient
import services.mongo.MongoDbManager
import utils.Contraseñas
import java.time.LocalDate
import java.time.LocalDateTime


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ControllerTest {


    private val usuario =  User(
        _id = "32",
        id = "37",
        name = "dsgs",
        username = "e23d",
        email = "juan",
        password = Contraseñas.encriptar("pepe"),
        tipoUser = User.TipoUsuario.CLIENTE,
        phone = "3453",
        website = "http://www.gfgd",
        )

    private val turno = Turno(
        _id = "123",
        fechaInicio =  LocalDateTime.parse("2022-02-15T00:00:00.000+05:30", Data.formatter),
        fechaFinal = LocalDateTime.parse("2024-12-16T00:00:00.000+05:30", Data.formatter),
        usuario_id = "32",

    )

    private val pedido = Pedido(
    _id = "123456789",
    estado = Pedido.TipoEstado.EN_PROCESO,
    fechaEntrada = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
    fechaSalidaProgramada = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
    fechaEntrega = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
    precio = 222.2,
    usuario_id = "32",
    )

    private val producto = Producto(
    marca = "Siemens",
    modelo =" Negro",
    precio = 118.6,
    stock = "2458",
    tipoProducto = Producto.TipoProducto.COMPLEMENTO,
    pedido_id = "123456789",
    )


    private var tareaPersonalizacion = TareaPersonalizacion(
    rigidez = 8.00,
    peso = 1.20,
    balance = 1.10,
    precio = 222.2,
    pedido_id = "123456789",
    )

    private var maquinaEncordar = MaquinaEncordar(
        marca = "Maquina marca Encordado 1",
        modelo = "Maquina modelo Encordado 1",
        fechaAdquisicion = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
        numeroSerie = "3345",
        tipo = MaquinaEncordar.TipoEncordaje.AUTOMATICA,
        tensionMaxima = 12.32,
        tensionMinima = 11.00,
        turno_id = "123",
    )

    private val maquinaPersonalizar =   MaquinaPersonalizar(
        marca = "Maquina marca Personalizacion 1",
        modelo = "Maquina modelo Personalizacion 1",
        fechaAdquisicion = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
        numeroSerie = "3345",
        swingweight = "true",
        balance = 12.32,
        rigidez = 11.00,
        turno_id = "123",
    )

     var userRepository =  UserRepository()
     var repoUser = UserRepositoryCache(CacheClient())
     var turnoRepository = TurnoRepository()
     var pedidoRepository = PedidoRepository()
     var tareasPersonalizacion = TareaPersonalizacionRepository()
     var tareasEncordadoRepository= TareaEncordadoRepository()
     var maquinaPersonalizarRepository = MaquinaPersonalizarRepository()
     var maquinaEncordarRepository= MaquinaEncordarRepository()
     var productoRepository= ProductoRepository()
     var ktorfitRepository =KtorfitRepository()



    var controller  = TennisLabController(userRepository,repoUser,turnoRepository,tareasPersonalizacion,tareasEncordadoRepository,
    pedidoRepository, ktorfitRepository, productoRepository, maquinaPersonalizarRepository, maquinaEncordarRepository)





    //------------------------------------------------USUARIOS-----------------------------------------------------

    @Test
    fun findAllUsers() = runTest {
        MongoDbManager.database.getCollection<User>().drop()

        controller.añadirUser(usuario)
        var res = controller.findAllUsers().toList()
        assertAll(
            { assertEquals(1, res.size) }
        )
        controller.deleteUser(usuario._id)
    }

    @Test
    fun createUser() = runTest {
        MongoDbManager.database.getCollection<User>().drop()

        controller.añadirUser(usuario)
        var list = controller.findAllUsers().toList()
        assertAll(
            { assertEquals(1, list.size) }
        )
        userRepository.delete(usuario._id)
        repoUser.delete(usuario._id)
    }

    @Test
    fun updateUser() = runTest {
        MongoDbManager.database.getCollection<User>().drop()

        val usuarioo =  User(
            _id = "32",
            id = "37",
            name = "dsgs",
            username = "e23d",
            email = "dani@gmail.com",
            password = Contraseñas.encriptar("pepe"),
            tipoUser = User.TipoUsuario.CLIENTE,
            phone = "3453",
            website = "http://www.gfgd",
        )
        controller.añadirUser(usuario)
        controller.updateUser(usuarioo)
        var list = controller.findAllUsers().toList()
        assertAll(
            { assertEquals(usuarioo.email, list[0].email) }
        )

        userRepository.delete(usuario._id)
        repoUser.delete(usuarioo._id)
    }

    @Test
    fun deleteUser() = runTest {
        MongoDbManager.database.getCollection<User>().drop()

        controller.añadirUser(usuario)
        var list = controller.findAllUsers().toList()
        assertAll(
            { assertEquals(1, list.size) }
        )
        userRepository.delete(usuario._id)
        repoUser.delete(usuario._id)
        var listBorrar = controller.findAllUsers().toList()

        assertAll(
            { assertNotEquals(listBorrar.size, list.size) }
        )

    }


    //------------------------------------------------TURNOS-----------------------------------------------------



    @Test
    fun findAllTurno() = runTest {
        MongoDbManager.database.getCollection<User>().drop()

        controller.añadirUser(usuario)
        controller.añadirTurnos(turno)
        var res = controller.findAllTurnos().toList()
        assertAll(
            { assertEquals(1, res.size) }
        )
        controller.deleteTurnos(turno._id)
        controller.deleteUser(usuario._id)
    }


    @Test
    fun createTurno() = runTest {
        MongoDbManager.database.getCollection<User>().drop()

        controller.añadirUser(usuario)
        controller.añadirTurnos(turno)
        var res = controller.findAllTurnos().toList()
        assertAll(
            { assertEquals(1, res.size) }
        )
        controller.deleteTurnos(turno._id)
        controller.deleteUser(usuario._id)
    }


    @Test
    fun updateTurno() = runTest {
        MongoDbManager.database.getCollection<User>().drop()

        val turnoo =  Turno(
            _id = "123",
            fechaInicio =  LocalDateTime.parse("2022-02-15T00:00:00.000+05:30", Data.formatter),
            fechaFinal = LocalDateTime.parse("2068-12-16T00:00:00.000+05:30", Data.formatter),
            usuario_id = "32",
        )
        controller.añadirUser(usuario)
        controller.añadirTurnos(turno)
        controller.actualizarTurnos(turnoo)
        var list = controller.findAllTurnos().toList()
        assertAll(
            { assertNotEquals(turno.fechaFinal, list[0].fechaFinal) }
        )

        controller.deleteTurnos(turno._id)
        controller.deleteUser(usuario._id)
    }

    @Test
    fun deleteTurno() = runTest {
        MongoDbManager.database.getCollection<User>().drop()

        controller.añadirUser(usuario)
        controller.añadirTurnos(turno)
        var list = controller.findAllTurnos().toList()
        assertAll(
            { assertEquals(1, list.size) }
        )
        controller.deleteTurnos(turno._id)
        controller.deleteUser(usuario._id)
        var listBorrar = controller.findAllTurnos().toList()

        assertAll(
            { assertNotEquals(listBorrar.size, list.size) }
        )

    }



    //------------------------------------------------PEDIDO-----------------------------------------------------


    @Test
    fun findAllPedido() = runTest {
        MongoDbManager.database.getCollection<User>().drop()

        controller.añadirUser(usuario)
        controller.añadirPedidos(pedido)
        var res = controller.findAllPedidos().toList()
        assertAll(
            { assertEquals(1, res.size) }
        )
        controller.deletePedido(pedido._id)
        controller.deleteUser(usuario._id)
    }


    @Test
    fun createPedido() = runTest {
        MongoDbManager.database.getCollection<User>().drop()

        controller.añadirUser(usuario)
        controller.añadirPedidos(pedido)
        var res = controller.findAllPedidos().toList()
        assertAll(
            { assertEquals(1, res.size) }
        )
        controller.deletePedido(pedido._id)
        controller.deleteUser(usuario._id)
    }


    @Test
    fun updatePedido() = runTest {
        MongoDbManager.database.getCollection<User>().drop()

        val pedidoo =  Pedido(
            _id = "123456789",
            estado = Pedido.TipoEstado.TERMINADO,
            fechaEntrada = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
            fechaSalidaProgramada = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
            fechaEntrega = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
            precio = 222.2,
            usuario_id = "32",
        )
        controller.añadirUser(usuario)
        controller.añadirPedidos(pedido)
        controller.actualizarPedido(pedidoo)
        var list = controller.findAllPedidos().toList()
        assertAll(
            { assertNotEquals(pedido.estado, list[0].estado) }
        )

        controller.deletePedido(turno._id)
        controller.deleteUser(usuario._id)
    }

    @Test
    fun deletePedido() = runTest {
        MongoDbManager.database.getCollection<User>().drop()
        MongoDbManager.database.getCollection<Pedido>().drop()
        controller.añadirUser(usuario)
        controller.añadirPedidos(pedido)
        var list = controller.findAllPedidos().toList()
        assertAll(
            { assertEquals(1, list.size) }
        )
        controller.deletePedido(pedido._id)
        controller.deleteUser(usuario._id)
        var listBorrar = controller.findAllPedidos().toList()

        assertAll(
            { assertNotEquals(listBorrar.size, list.size) }
        )

    }

    //------------------------------------------------PRODUCTO-----------------------------------------------------



    @Test
    fun findAllProducto() = runTest {
        MongoDbManager.database.getCollection<User>().drop()
        MongoDbManager.database.getCollection<Pedido>().drop()
        MongoDbManager.database.getCollection<Producto>().drop()
        controller.añadirUser(usuario)
        controller.añadirPedidos(pedido)
        controller.añadirProducto(producto)

        var res = controller.findAllProducto().toList()
        assertAll(
            { assertEquals(1, res.size) }
        )
        controller.deleteProducto(producto._id)
        controller.deletePedido(pedido._id)
        controller.deleteUser(usuario._id)
    }



    @Test
    fun createProducto() = runTest {
        MongoDbManager.database.getCollection<User>().drop()

        controller.añadirUser(usuario)
        controller.añadirPedidos(pedido)
        controller.añadirProducto(producto)

        var res = controller.findAllProducto().toList()
        assertAll(
            { assertEquals(1, res.size) }
        )
        controller.deleteProducto(producto._id)
        controller.deletePedido(pedido._id)
        controller.deleteUser(usuario._id)
    }


    @Test
    fun updateProducto() = runTest {
        MongoDbManager.database.getCollection<User>().drop()
        MongoDbManager.database.getCollection<Pedido>().drop()
        MongoDbManager.database.getCollection<Producto>().drop()
        val productoo =  Producto(
            marca = "Nike",
            modelo =" Negro",
            precio = 118.6,
            stock = "2458",
            tipoProducto = Producto.TipoProducto.COMPLEMENTO,
            pedido_id = "123456789",
        )
        controller.añadirUser(usuario)
        controller.añadirPedidos(pedido)
        controller.añadirProducto(producto)
        controller.actualizarProducto(productoo)
        var list = controller.findAllProducto().toList()
        assertAll(
            { assertNotEquals(productoo.marca, list[0].marca) }
        )

        controller.deleteProducto(producto._id)
        controller.deletePedido(turno._id)
        controller.deleteUser(usuario._id)
    }


    @Test
    fun deleteProducto() = runTest {
        MongoDbManager.database.getCollection<User>().drop()
        MongoDbManager.database.getCollection<Pedido>().drop()
        MongoDbManager.database.getCollection<Producto>().drop()
        controller.añadirUser(usuario)
        controller.añadirPedidos(pedido)
        controller.añadirProducto(producto)

        var list = controller.findAllProducto().toList()
        assertAll(
            { assertEquals(1, list.size) }
        )
        controller.deleteProducto(producto._id)
        controller.deletePedido(pedido._id)
        controller.deleteUser(usuario._id)
        var listBorrar = controller.findAllProducto().toList()

        assertAll(
            { assertNotEquals(listBorrar.size, list.size) }
        )

    }



    //------------------------------------------------TAREA PERSONALIZAR-----------------------------------------------------

    @Test
    fun findAllTareaPersonalizar() = runTest {
        MongoDbManager.database.getCollection<User>().drop()
        MongoDbManager.database.getCollection<Pedido>().drop()
        MongoDbManager.database.getCollection<TareaPersonalizacion>().drop()

        controller.añadirUser(usuario)
        controller.añadirPedidos(pedido)
        controller.añadirTareasPersonalizacion(tareaPersonalizacion)

        var res = controller.findAllTareaPersonalizar().toList()
        assertAll(
            { assertEquals(1, res.size) }
        )
        controller.deleteTareasPersonalizacion(tareaPersonalizacion._id)
        controller.deletePedido(pedido._id)
        controller.deleteUser(usuario._id)
    }


    @Test
    fun createTareaPersonalizar() = runTest {
        MongoDbManager.database.getCollection<User>().drop()
        MongoDbManager.database.getCollection<Pedido>().drop()
        MongoDbManager.database.getCollection<TareaPersonalizacion>().drop()
        controller.añadirUser(usuario)
        controller.añadirPedidos(pedido)
        controller.añadirTareasPersonalizacion(tareaPersonalizacion)

        var res = controller.findAllTareaPersonalizar().toList()
        assertAll(
            { assertEquals(1, res.size) }
        )
        controller.deleteTareasPersonalizacion(tareaPersonalizacion._id)
        controller.deletePedido(pedido._id)
        controller.deleteUser(usuario._id)
    }


    @Test
    fun updateTareaPersonalizacion() = runTest {
        var tareaPersonalizacionn = TareaPersonalizacion(
            rigidez = 8.00,
            peso = 14.20,
            balance = 1.10,
            precio = 222.2,
            pedido_id = "123456789",
        )
        controller.añadirUser(usuario)
        controller.añadirPedidos(pedido)
        controller.añadirTareasPersonalizacion(tareaPersonalizacionn)
        controller.actualizarTareasPersonalizacion(tareaPersonalizacionn)
        var list = controller.findAllTareaPersonalizar().toList()
        assertAll(
            { assertNotEquals(tareaPersonalizacion.peso, list[0].peso) }
        )

        controller.deleteTareasPersonalizacion(tareaPersonalizacion._id)
        controller.deletePedido(turno._id)
        controller.deleteUser(usuario._id)
    }

    @Test
    fun deleteTareaPersonalizacion() = runTest {
        MongoDbManager.database.getCollection<User>().drop()
        MongoDbManager.database.getCollection<Pedido>().drop()
        MongoDbManager.database.getCollection<TareaPersonalizacion>().drop()


        controller.añadirUser(usuario)
        controller.añadirPedidos(pedido)
        controller.añadirTareasPersonalizacion(tareaPersonalizacion)

        var list = controller.findAllTareaPersonalizar().toList()
        assertAll(
            { assertEquals(1, list.size) }
        )
        controller.deleteTareasPersonalizacion(tareaPersonalizacion._id)
        controller.deletePedido(pedido._id)
        controller.deleteUser(usuario._id)
        var listBorrar = controller.findAllTareaPersonalizar().toList()

        assertAll(
            { assertNotEquals(listBorrar.size, list.size) }
        )

    }

    //------------------------------------------------MAQUINA ENCORDAR-----------------------------------------------------


    @Test
    fun findAllMaquinaEncordarEncordar() = runTest {
        MongoDbManager.database.getCollection<User>().drop()
        MongoDbManager.database.getCollection<Turno>().drop()
        MongoDbManager.database.getCollection<MaquinaEncordar>().drop()
        controller.añadirUser(usuario)
        controller.añadirTurnos(turno)
        controller.añadirMaquinaEncordar(maquinaEncordar)

        var res = controller.findAllMaquinaEncordar().toList()
        assertAll(
            { assertEquals(1, res.size) }
        )
        controller.deleteMaquinaEncordar(maquinaEncordar._id)
        controller.deleteTurnos(turno._id)
        controller.deleteUser(usuario._id)
    }


    @Test
    fun createMaquinaEncordar() = runTest {
        MongoDbManager.database.getCollection<User>().drop()
        MongoDbManager.database.getCollection<Turno>().drop()
        MongoDbManager.database.getCollection<MaquinaEncordar>().drop()


        controller.añadirUser(usuario)
        controller.añadirTurnos(turno)
        controller.añadirMaquinaEncordar(maquinaEncordar)

        var res = controller.findAllMaquinaEncordar().toList()
        assertAll(
            { assertEquals(1, res.size) }
        )
        controller.deleteMaquinaEncordar(maquinaEncordar._id)
        controller.deleteTurnos(turno._id)
        controller.deleteUser(usuario._id)
    }


    @Test
    fun updateMaquinaEncordar() = runTest {
        MongoDbManager.database.getCollection<User>().drop()
        MongoDbManager.database.getCollection<Turno>().drop()
        MongoDbManager.database.getCollection<MaquinaEncordar>().drop()
        var maquinaEncordarr = MaquinaEncordar(
            marca = "Maquina marca Encordado 12",
            modelo = "Maquina modelo Encordado 1",
            fechaAdquisicion = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
            numeroSerie = "3345",
            tipo = MaquinaEncordar.TipoEncordaje.AUTOMATICA,
            tensionMaxima = 12.32,
            tensionMinima = 11.00,
            turno_id = "123",
        )
        controller.añadirUser(usuario)
        controller.añadirTurnos(turno)
        controller.añadirMaquinaEncordar(maquinaEncordar)
        controller.actualizarMaquinaEncordar(maquinaEncordarr)
        var list = controller.findAllMaquinaEncordar().toList()
        assertAll(
            { assertNotEquals(maquinaEncordarr.marca, list[0].marca) }
        )

        controller.deleteMaquinaEncordar(maquinaEncordar._id)
        controller.deleteTurnos(turno._id)
        controller.deleteUser(usuario._id)
    }



    @Test
    fun deleteMaquinaEncordar() = runTest {
        MongoDbManager.database.getCollection<User>().drop()
        MongoDbManager.database.getCollection<Turno>().drop()
        MongoDbManager.database.getCollection<MaquinaEncordar>().drop()


        controller.añadirUser(usuario)
        controller.añadirTurnos(turno)
        controller.añadirMaquinaEncordar(maquinaEncordar)

        var list = controller.findAllMaquinaEncordar().toList()
        assertAll(
            { assertEquals(1, list.size) }
        )
        controller.deleteMaquinaEncordar(maquinaEncordar._id)
        controller.deleteTurnos(turno._id)
        controller.deleteUser(usuario._id)
        var listBorrar = controller.findAllMaquinaEncordar().toList()

        assertAll(
            { assertNotEquals(listBorrar.size, list.size) }
        )

    }

    //------------------------------------------------MAQUINA PERSONALIZAR-----------------------------------------------------




    @Test
    fun findAllMaquinaEncordarPersonalizar() = runTest {
        MongoDbManager.database.getCollection<User>().drop()
        MongoDbManager.database.getCollection<Turno>().drop()
        MongoDbManager.database.getCollection<MaquinaPersonalizar>().drop()

        controller.añadirUser(usuario)
        controller.añadirTurnos(turno)
        controller.añadirMaquinaPersonalizacion(maquinaPersonalizar)

        var res = controller.findAllMaquinaPersonalizar().toList()
        assertAll(
            { assertEquals(1, res.size) }
        )
        controller.deleteTareasPersonalizacion(maquinaPersonalizar._id)
        controller.deleteTurnos(turno._id)
        controller.deleteUser(usuario._id)
    }



    @Test
    fun createMaquinaPersonalizar() = runTest {
        MongoDbManager.database.getCollection<User>().drop()
        MongoDbManager.database.getCollection<Turno>().drop()
        MongoDbManager.database.getCollection<MaquinaPersonalizar>().drop()


        controller.añadirUser(usuario)
        controller.añadirTurnos(turno)
        controller.añadirMaquinaPersonalizacion(maquinaPersonalizar)

        var res = controller.findAllMaquinaPersonalizar().toList()
        assertAll(
            { assertEquals(1, res.size) }
        )
        controller.deleteMaquinaPersonalizacion(maquinaPersonalizar._id)
        controller.deleteTurnos(turno._id)
        controller.deleteUser(usuario._id)
    }


    @Test
    fun updateMaquinaPersonalizar() = runTest {
        MongoDbManager.database.getCollection<User>().drop()
        MongoDbManager.database.getCollection<Turno>().drop()
        MongoDbManager.database.getCollection<MaquinaPersonalizar>().drop()

        var maquinaPersonalizarr = MaquinaPersonalizar(
            marca = "Maquina marca Personalizacion 16",
            modelo = "Maquina modelo Personalizacion 1",
            fechaAdquisicion = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
            numeroSerie = "3345",
            swingweight = "true",
            balance = 12.32,
            rigidez = 11.00,
            turno_id = "123",
        )
        controller.añadirUser(usuario)
        controller.añadirTurnos(turno)
        controller.añadirMaquinaPersonalizacion(maquinaPersonalizar)
        controller.actualizarMaquinaPersonalizacion(maquinaPersonalizarr)
        var list = controller.findAllMaquinaPersonalizar().toList()
        assertAll(
            { assertNotEquals(maquinaPersonalizarr.marca, list[0].marca) }
        )

        controller.deleteMaquinaEncordar(maquinaEncordar._id)
        controller.deleteTurnos(turno._id)
        controller.deleteUser(usuario._id)
    }


    @Test
    fun deleteMaquinaPersonalizar() = runTest {
        MongoDbManager.database.getCollection<User>().drop()
        MongoDbManager.database.getCollection<Turno>().drop()
        MongoDbManager.database.getCollection<MaquinaPersonalizar>().drop()


        controller.añadirUser(usuario)
        controller.añadirTurnos(turno)
        controller.añadirMaquinaPersonalizacion(maquinaPersonalizar)

        var list = controller.findAllMaquinaPersonalizar().toList()
        assertAll(
            { assertEquals(1, list.size) }
        )
        controller.deleteMaquinaPersonalizacion(maquinaPersonalizar._id)
        controller.deleteTurnos(turno._id)
        controller.deleteUser(usuario._id)
        var listBorrar = controller.findAllMaquinaPersonalizar().toList()

        assertAll(
            { assertNotEquals(listBorrar.size, list.size) }
        )

    }


}