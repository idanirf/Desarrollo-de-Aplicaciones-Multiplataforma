package controllers

import es.drodriguez.com.tennislabspring.controller.TennisLabController
import es.drodriguez.com.tennislabspring.data.Data
import es.drodriguez.com.tennislabspring.models.*
import es.drodriguez.com.tennislabspring.repositories.*
import es.drodriguez.com.tennislabspring.utils.Contraseñas
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.bson.types.ObjectId
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate
import java.time.LocalDateTime


@ExtendWith(MockitoExtension::class)
class ControllerTest {

    @Mock
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var turnoRepository: TurnoRepository

    @Mock
    lateinit var tareaEncordadoRepository: TareaEncordadoRepository

    @Mock
    lateinit var tareaPersonalizarRepository: TareaPersonalizarRepository

    @Mock
    lateinit var productoRepository: ProductoRepository

    @Mock
    lateinit var pedidoRepository: PedidoRepository

    @Mock
    lateinit var maquinaEncordadoRepository: MaquinaEncordarRepository

    @Mock
    lateinit var maquinaPersonalizarRepository: MaquinaPersonalizarRepository

    @Mock
    lateinit var userRepositoryCache: UserRepositoryCache

    @Mock
    lateinit var restRepository: RestRepository

    @InjectMocks
    lateinit var tennisLabController: TennisLabController



    private val usuario =  User(
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
        fechaInicio =  LocalDateTime.parse("2022-02-15T00:00:00.000+05:30", Data.formatter),
        fechaFinal =  LocalDateTime.parse("2023-02-15T00:00:00.000+05:30", Data.formatter),
    )

    private val pedido = Pedido(
        estado = Pedido.TipoEstado.TERMINADO,
        fechaEntrada = LocalDateTime.parse("2022-02-15T00:00:00.000+05:30", Data.formatter),
        fechaSalidaProgramada = LocalDateTime.parse("2023-02-15T00:00:00.000+05:30", Data.formatter),
        fechaEntrega = LocalDateTime.parse("2023-02-15T00:00:00.000+05:30", Data.formatter),
        precio = 222.2
    )

    private val tareaEncordar = TareaEncordado(
    precio = 8.00,
    tensionVertical = 1.20,
    cordajeVertical = "Cordaje Vertical",
    tensionHorizontal = 1.10,
    cordajeHorizontal = "Cordaje Horizontal",
    nudos = TareaEncordado.NumeroNudos.DOS,
    )

    private val tareaPersonalizar =  TareaPersonalizacion(
        rigidez = 8.00,
        peso = 1.20,
        balance = 1.10,
        precio = 222.2
    )


    private val producto =  Producto(
        marca = "Nike",
        modelo =" Babolat",
        precio = 200.6,
        stock = "3345",
        tipoProducto = Producto.TipoProducto.RAQUETA,
        )

    private val maquinaPersonalizar = MaquinaPersonalizar(
        marca = "Maquina marca Personalizacion 1",
        modelo = "Maquina modelo Personalizacion 1",
        fechaAdquisicion = LocalDateTime.parse("2023-02-15T00:00:00.000+05:30", Data.formatter),
        numeroSerie = "3345",
        swingweight = "true",
        balance = 12.32,
        rigidez = 11.00,
    )


    private val maquinaEncordar =  MaquinaEncordar(
        marca = "Maquina marca Encordado 1",
        modelo = "Maquina modelo Encordado 1",
        fechaAdquisicion = LocalDateTime.parse("2023-02-15T00:00:00.000+05:30", Data.formatter),
        numeroSerie = "3345",
        tipo = MaquinaEncordar.TipoEncordaje.AUTOMATICA,
        tensionMaxima = 12.32,
        tensionMinima = 11.00,
    )


    //______________________________________________USUSARIOS--------------------------------------------------

    @Test
    fun testInsertarUsuario() = runTest {
        val user = User(
            id = "55",
            name = "Jorge",
            username = "Juan",
            email = "juan@gmail.com",
            password = "hola",
            tipoUser = User.TipoUsuario.ADMIN,
            phone = "736363736",
            website = "http://www.juan",
        )
        tennisLabController.insertarUsuario(user)
        verify(userRepository, times(1)).save(user)
    }

    @Test
    fun testBorrarUsuario() = runTest {
        val id = ObjectId()
        tennisLabController.borrarUsuario(id)
        verify(userRepository, times(1)).deleteById(id)
    }

    @Test
    fun testActualizarUsuario() = runTest {
        val user = User(
            _id = usuario._id,
            id = "55",
            name = "Jorge",
            username = "Pepe",
            email = "juan@gmail.com",
            password = "hola",
            tipoUser = User.TipoUsuario.ADMIN,
            phone = "736363736",
            website = "http://www.juan",)
        tennisLabController.actualizarUsuario(user)
        verify(userRepository, times(1)).save(user)
    }

    @Test
    fun testFindByUserId() = runTest {
        tennisLabController.findByUserId(usuario._id)
        verify(userRepository, times(1)).findById(usuario._id)
    }


    @Test
    fun testObtenerTodosLosUsuarios() = runTest {
        tennisLabController.obtenerTodosLosUsuarios()
        verify(userRepository, times(1)).findAll()
    }










    //______________________________________________TURNOS--------------------------------------------------

    @Test
    fun testInsertarTurno() = runTest {
        val turno = Turno(
            _id = turno._id ,
            fechaInicio = LocalDateTime.parse("2023-02-15T00:00:00.000+05:30", Data.formatter),
            fechaFinal =LocalDateTime.parse("2023-02-15T00:00:00.000+05:30", Data.formatter),
        )
        tennisLabController.insertarTurno(turno)
        verify(turnoRepository, times(1)).save(turno)
    }

    @Test
    fun testBorrarTurno() = runTest {
        val id = ObjectId()
        tennisLabController.borrarTurno(id)
        verify(turnoRepository, times(1)).deleteById(id)
    }

    @Test
    fun testActualizarTurno() = runTest {
        val turno = Turno(
            fechaInicio = LocalDateTime.parse("2023-02-15T00:00:00.000+05:30", Data.formatter),
            fechaFinal =LocalDateTime.parse("2023-02-15T00:00:00.000+05:30", Data.formatter),
        )
        tennisLabController.actualizarTurno(turno)
        verify(turnoRepository, times(1)).save(turno)
    }

    @Test
    fun testFindByTurnoId() = runTest {
        tennisLabController.findByTurnoId(turno._id)
        verify(turnoRepository, times(1)).findById(turno._id)
    }


    @Test
    fun testObtenerTodosLosTurnos() = runTest {
        tennisLabController.obtenerTodosLosTurnos()
        verify(turnoRepository, times(1)).findAll()
    }





    //______________________________________________PEDIDO--------------------------------------------------

    @Test
    fun testInsertarPedido() = runTest {
        val pedidoo = Pedido(
            estado = Pedido.TipoEstado.EN_PROCESO,
            fechaEntrada = LocalDateTime.parse("2023-02-15T00:00:00.000+05:30", Data.formatter),
            fechaSalidaProgramada = LocalDateTime.parse("2023-02-15T00:00:00.000+05:30", Data.formatter),
            fechaEntrega = LocalDateTime.parse("2023-02-15T00:00:00.000+05:30", Data.formatter),
            precio = 222.2
        )
        tennisLabController.insertarPedido(pedidoo)
        verify(pedidoRepository, times(1)).save(pedidoo)
    }

    @Test
    fun testBorrarPedido() = runTest {
        val id = ObjectId()
        tennisLabController.borrarPedido(id)
        verify(pedidoRepository, times(1)).deleteById(id)
    }

    @Test
    fun testActualizarPedido() = runTest {
        val pedidoooo = Pedido(
            _id = pedido._id ,
            estado = Pedido.TipoEstado.TERMINADO,
            fechaEntrada = LocalDateTime.parse("2023-02-15T00:00:00.000+05:30", Data.formatter),
            fechaSalidaProgramada = LocalDateTime.parse("2023-02-15T00:00:00.000+05:30", Data.formatter),
            fechaEntrega = LocalDateTime.parse("2023-02-15T00:00:00.000+05:30", Data.formatter),
            precio = 333.2
        )
        tennisLabController.actualizarPedido(pedidoooo)
        verify(pedidoRepository, times(1)).save(pedidoooo)
    }

    @Test
    fun testFindByPedidoId() = runTest {
        tennisLabController.findByPedidoId(pedido._id)
        verify(pedidoRepository, times(1)).findById(pedido._id)
    }


    @Test
    fun testObtenerTodosLosPedidos() = runTest {
        tennisLabController.obtenerTodosLosPedido()
        verify(pedidoRepository, times(1)).findAll()
    }






    //______________________________________________TAREA ENCORDAR--------------------------------------------------

    @Test
    fun testInsertarTareaEncordar() = runTest {
        val tarea = TareaEncordado(
            precio = 8.00,
            tensionVertical = 1.20,
            cordajeVertical = "Cordaje Vertical",
            tensionHorizontal = 1.10,
            cordajeHorizontal = "Cordaje Horizontal",
            nudos = TareaEncordado.NumeroNudos.CUATRO,
        )
        tennisLabController.insertarTareaEncordado(tarea)
        verify(tareaEncordadoRepository, times(1)).save(tarea)
    }

    @Test
    fun testBorrarTareaEncordar() = runTest {
        val id = ObjectId()
        tennisLabController.borrarTareaEncordado(id)
        verify(tareaEncordadoRepository, times(1)).deleteById(id)
    }

    @Test
    fun testActualizarTareaEncordar() = runTest {
        val tarea = TareaEncordado(
            _id = tareaEncordar._id,
            precio = 8.00,
            tensionVertical = 1.20,
            cordajeVertical = "Cordaje Vertical",
            tensionHorizontal = 1.10,
            cordajeHorizontal = "Cordaje Horizontal",
            nudos = TareaEncordado.NumeroNudos.CUATRO,
        )
        tennisLabController.actualizarTareaEncordado(tarea)
        verify(tareaEncordadoRepository, times(1)).save(tarea)
    }

    @Test
    fun testFindByTareaEncordarId() = runTest {
        tennisLabController.findByTareaEncordadoId(tareaEncordar)
        verify(tareaEncordadoRepository, times(1)).findById(tareaEncordar._id)
    }


    @Test
    fun testObtenerTodosLosTareaEncordar() = runTest {
        tennisLabController.obtenerTodosLosTareaEncordado()
        verify(tareaEncordadoRepository, times(1)).findAll()
    }





    //______________________________________________TAREA PERSONALIZAR--------------------------------------------------

    @Test
    fun testInsertarTareaPersonalizar() = runTest {
        val tarea =  TareaPersonalizacion(
            rigidez = 8.00,
            peso = 1.20,
            balance = 1.10,
            precio = 222.2
        )
        tennisLabController.insertarTareaPersonalizar(tarea)
        verify(tareaPersonalizarRepository, times(1)).save(tarea)
    }

    @Test
    fun testBorrarTareaEPersonalizar() = runTest {
        val id = ObjectId()
        tennisLabController.borrarTareaPersonalizar(id)
        verify(tareaPersonalizarRepository, times(1)).deleteById(id)
    }

    @Test
    fun testActualizarTareaPersonalizar() = runTest {
        val tarea = TareaPersonalizacion(
            _id =tareaPersonalizar._id ,
            rigidez = 8.00,
            peso = 1.20,
            balance = 1.10,
            precio = 222.2
        )
        tennisLabController.actualizarTareaPersonalizar(tarea)
        verify(tareaPersonalizarRepository, times(1)).save(tarea)
    }

    @Test
    fun testFindByTareaPersonalizarId() = runTest {
        tennisLabController.findByTareaPersonalizarId(tareaPersonalizar)
        verify(tareaPersonalizarRepository, times(1)).findById(tareaPersonalizar._id)
    }


    @Test
    fun testObtenerTodosLosTareaPersonalizar() = runTest {
        tennisLabController.obtenerTodosLosTareaPersonalizar()
        verify(tareaPersonalizarRepository, times(1)).findAll()
    }







    //______________________________________________PRODUCTO--------------------------------------------------

    @Test
    fun testInsertarProducto() = runTest {
        val productoo =  Producto(
            marca = "Nike",
            modelo =" Babolat",
            precio = 200.6,
            stock = "3345",
            tipoProducto = Producto.TipoProducto.RAQUETA,
        )
        tennisLabController.insertarProducto(productoo)
        verify(productoRepository, times(1)).save(productoo)
    }

    @Test
    fun testBorrarProducto() = runTest {
        val id = ObjectId()
        tennisLabController.borrarProducto(id)
        verify(productoRepository, times(1)).deleteById(id)
    }

    @Test
    fun testActualizarProducto() = runTest {
        val productoo =  Producto(
            _id = producto._id,
            marca = "Nike",
            modelo =" Babolat",
            precio = 200.6,
            stock = "3345",
            tipoProducto = Producto.TipoProducto.RAQUETA,
        )
        tennisLabController.actualizarProducto(productoo)
        verify(productoRepository, times(1)).save(productoo)
    }

    @Test
    fun testFindByProductoId() = runTest {
        tennisLabController.findByProductoId(producto)
        verify(productoRepository, times(1)).findById(producto._id)
    }


    @Test
    fun testObtenerTodosLosProducto() = runTest {
        tennisLabController.obtenerTodosLosProducto()
        verify(productoRepository, times(1)).findAll()
    }






    //______________________________________________MAQUINA PERSONALIZAR--------------------------------------------------

    @Test
    fun testInsertarMaquinaPersonalizar() = runTest {
        val maquinaa =  MaquinaPersonalizar(
            marca = "Maquina marca Personalizacion 1",
            modelo = "Maquina modelo Personalizacion 1",
            fechaAdquisicion = LocalDateTime.parse("2023-02-15T00:00:00.000+05:30", Data.formatter),
            numeroSerie = "3345",
            swingweight = "true",
            balance = 12.32,
            rigidez = 11.00,
        )
        tennisLabController.insertarMaquinaPersonalizar(maquinaa)
        verify(maquinaPersonalizarRepository, times(1)).save(maquinaa)
    }

    @Test
    fun testBorrarMaquinaPersonalizar() = runTest {
        val id = ObjectId()
        tennisLabController.borrarMaquinaPersonalizar(id)
        verify(maquinaPersonalizarRepository, times(1)).deleteById(id)
    }

    @Test
    fun testActualizarMaquinaPersonalizar() = runTest {
        val maquinaa =  MaquinaPersonalizar(
            _id = maquinaPersonalizar._id ,
            marca = "Maquina marca Personalizacion 123",
            modelo = "Maquina modelo Personalizacion 123",
            fechaAdquisicion = LocalDateTime.parse("2023-02-15T00:00:00.000+05:30", Data.formatter),
            numeroSerie = "3345",
            swingweight = "true",
            balance = 1263.32,
            rigidez = 1123.00,
        )
        tennisLabController.actualizarMaquinaPersonalizar(maquinaa)
        verify(maquinaPersonalizarRepository, times(1)).save(maquinaa)
    }

    @Test
    fun testFindByMaquinaPersonalizarId() = runTest {
        tennisLabController.findByMaquinaPersonalizarId(maquinaPersonalizar._id)
        verify(maquinaPersonalizarRepository, times(1)).findById(maquinaPersonalizar._id)
    }


    @Test
    fun testObtenerTodosLosMaquinaPersonalizar() = runTest {
        tennisLabController.obtenerTodosLosMaquinaPersonalizar()
        verify(maquinaPersonalizarRepository, times(1)).findAll()
    }





    //______________________________________________MAQUINA ENCORDAR--------------------------------------------------

    @Test
    fun testInsertarMaquinaEncordar() = runTest {
        val maquinaa =  MaquinaEncordar(
            marca = "Maquina marca Encordado 1",
            modelo = "Maquina modelo Encordado 1",
            fechaAdquisicion = LocalDateTime.parse("2023-02-15T00:00:00.000+05:30", Data.formatter),
            numeroSerie = "3345",
            tipo = MaquinaEncordar.TipoEncordaje.AUTOMATICA,
            tensionMaxima = 12.32,
            tensionMinima = 11.00,
        )
        tennisLabController.insertarMaquinaEncordado(maquinaa)
        verify(maquinaEncordadoRepository, times(1)).save(maquinaa)
    }

    @Test
    fun testBorrarMaquinaEncordar() = runTest {
        val id = ObjectId()
        tennisLabController.borrarMaquinaEncordado(id)
        verify(maquinaEncordadoRepository, times(1)).deleteById(id)
    }

    @Test
    fun testActualizarMaquinaEncordar() = runTest {
        val maquinaa =  MaquinaEncordar(
            _id = maquinaEncordar._id,
            marca = "Maquina marca Encordado 1",
            modelo = "Maquina modelo Encordado 4    ",
            fechaAdquisicion =LocalDateTime.parse("2023-02-15T00:00:00.000+05:30", Data.formatter),
            numeroSerie = "24545",
            tipo = MaquinaEncordar.TipoEncordaje.MANUAL,
            tensionMaxima = 12.32,
            tensionMinima = 11.00,
        )
        tennisLabController.actualizarMaquinaEncordado(maquinaa)
        verify(maquinaEncordadoRepository, times(1)).save(maquinaa)
    }

    @Test
    fun testFindByMaquinaEncordarId() = runTest {
        tennisLabController.findByMaquinaEncordadoId(maquinaEncordar._id)
        verify(maquinaEncordadoRepository, times(1)).findById(maquinaEncordar._id)
    }


    @Test
    fun testObtenerTodosLosMaquinaEncordar() = runTest {
        tennisLabController.obtenerTodosLosMaquinaEncordado()
        verify(maquinaEncordadoRepository, times(1)).findAll()
    }
}