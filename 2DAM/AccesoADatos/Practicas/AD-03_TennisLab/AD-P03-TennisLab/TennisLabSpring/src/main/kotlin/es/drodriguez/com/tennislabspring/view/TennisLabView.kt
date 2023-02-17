package view


import dto.TareaDto
import es.drodriguez.com.tennislabspring.controller.TennisLabController
import es.drodriguez.com.tennislabspring.data.Data
import es.drodriguez.com.tennislabspring.data.Listados
import es.drodriguez.com.tennislabspring.dto.ProductoDto
import es.drodriguez.com.tennislabspring.dto.pedidoToDto
import es.drodriguez.com.tennislabspring.dto.productoToDto
import es.drodriguez.com.tennislabspring.models.*
import es.drodriguez.com.tennislabspring.utils.Contraseñas
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.toList
import org.springframework.beans.factory.annotation.Autowired
import utils.ficheros.FicheroJsonAsignaciones
import utils.ficheros.FicheroJsonPedidosCompletados
import utils.ficheros.FicheroJsonProductos
import utils.ficheros.FicheroJsonServicios
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.system.exitProcess





class TennisLabView
    @Autowired constructor( private var tennisLabController: TennisLabController
) {
   private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private lateinit var usuarioo : User
    private var pedido : Pedido? = null
    private lateinit var pedidoCliente : Pedido







    suspend fun login() {
        val emailChannel = Channel<String>()
        val passwordChannel = Channel<String>()
        val email : String
        val password : String

        println("Iniciar Sesión")

        runBlocking {
            launch {
                var email: String
                do {
                    println("Correo: ")
                    email = readln()
                } while (!email.matches(Regex("[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,5}")))
                emailChannel.send(email)
            }


            launch {
                println("Contraseña: ")
                val password = readln()
                passwordChannel.send(password)
            }
            email = emailChannel.receive()
            password = passwordChannel.receive()


            val user = tennisLabController.getUsersByEmail(email, password)
            if (user != null) {
                usuarioo = user

                pedidoCliente = Pedido(
                    estado = when (usuarioo.tipoUser) {
                        User.TipoUsuario.CLIENTE -> Pedido.TipoEstado.ENTRADA
                        User.TipoUsuario.TRABAJADOR -> Pedido.TipoEstado.EN_PROCESO
                        User.TipoUsuario.ADMIN -> Pedido.TipoEstado.EN_PROCESO
                    },
                    fechaEntrada = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
                    fechaSalidaProgramada = LocalDateTime.parse("2023-08-27T00:00:00.000+05:30", Data.formatter),
                    fechaEntrega = LocalDateTime.parse("2023-12-27T00:00:00.000+05:30", Data.formatter),
                    precio = 34.3,
                    usuario_id = usuarioo
                )
                tennisLabController.insertarPedido(pedidoCliente)

                bajarApi()

                launch {
                    when (usuarioo.tipoUser) {
                        User.TipoUsuario.CLIENTE -> accionesCliente()
                        User.TipoUsuario.TRABAJADOR -> accionesEncordador()
                        User.TipoUsuario.ADMIN -> accionesAdministrador()
                    }


                    listasJson()
                    println("Inicio de sesión terminado")
                    exitProcess(0)
                }
            }
        }

    }






    suspend fun bajarApi(){
        val list = tennisLabController.getAllApiUsers().toList()
        list.forEach {
            println("Insetando usuarios")
            tennisLabController.insertarUsuario(it)
            println(it)
        }

        val listaTareas = tennisLabController.getAllApiTareas().toList()
        listaTareas.forEach {
            Listados.servicios.add(it)
        }
    }


    suspend fun accionesCliente(){
        var pedidoCliente = Pedido(
            estado = Pedido.TipoEstado.EN_PROCESO,
            fechaEntrada = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
            fechaSalidaProgramada = LocalDateTime.parse("2023-08-27T00:00:00.000+05:30", Data.formatter),
            fechaEntrega = LocalDateTime.parse("2023-12-27T00:00:00.000+05:30", Data.formatter),
            precio = 34.3,
            usuario_id = usuarioo
        )
        tennisLabController.insertarPedido(pedidoCliente)
        realizarPedidoCliente()

    }

    private suspend fun accionesEncordador(){
        realizarPedidoCliente()
        comprobarPedidoCliente()
        cancelarPedidoCliente()
        consultarTodosPedidos()
        consultarTodosTurnos()
        consultarTodasMaquinas()
    }

    private suspend fun accionesAdministrador(){
        realizarPedidoCliente()
        comprobarPedidoCliente()
        cancelarPedidoCliente()
        consultarTodosPedidos()
        consultarTodosTurnos()
        consultarTodasMaquinas()
        crearUsuario()
        crearTurnos()
        crearMaquinas()
        borrarUsuario()
        borrarTurnos()
        borrarMaquinas()
    }

var user : User? = null
    private suspend fun crearUsuario() {
        println("CREAR USUARIO")
         user = User(
            id = "557",
            name = "pepe",
            username = "pepe345",
            email = "pepe@gmail.com",
            password = Contraseñas.encriptar("pepereina"),
            tipoUser = User.TipoUsuario.CLIENTE,
            phone = "665142892",
            website = "http://www.pepe.com",

            )
        tennisLabController.insertarUsuario(user!!)
        val userr = tennisLabController.findByUserId(user!!._id)
        if (userr != null) {
            println("User creado correctamento $userr")
        }else {
            println("Error al crear user: $userr")
        }
    }


    private suspend fun borrarUsuario() {
        println("BORRAR USUARIO")
        val user = tennisLabController.findByUserId(user!!._id)
        if (user != null) {
            tennisLabController.borrarUsuario(user._id)
            println("Usuario borrado correctamento $user")
        }else {
            println("No se encuentra el user con id 555 ")
        }

    }
    var turno : Turno? = null
    private suspend fun crearTurnos() {
        println("CREAR TURNO ADMIN")
         turno = Turno(
            fechaInicio = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
            fechaFinal = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
            usuario_id = usuarioo
        )
        tennisLabController.insertarTurno(turno!!)
        val turnoo = tennisLabController.findByTurnoId(turno!!._id)
        if (turnoo != null) {
            println("Turno creado correctamento $turno")
        }else {
            println("Error al crear turno: $turno")
        }
    }

    private suspend fun borrarTurnos() {
        println("BORRAR TURNO ADMIN")
        val turnoo = tennisLabController.findByTurnoId(turno!!._id)
        if (turnoo != null) {
            tennisLabController.borrarTurno(turnoo._id)
            println("Turno borrado correctamento $turnoo")
        }else {
            println("No se encuentra el turno con id 999 ")
        }

    }

    var maquinaPersonalizar : MaquinaPersonalizar? = null
    var maquinaEncordar : MaquinaEncordar? = null

    private suspend fun crearMaquinas() {
        println("CREAR MAQUINA ENCORDAR")
        val turno = tennisLabController.findByTurnoId(turno!!._id)
        maquinaEncordar = MaquinaEncordar(
            marca = "Siemens",
            modelo = "Sparta",
            fechaAdquisicion = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
            numeroSerie = "20234",
            tipo = MaquinaEncordar.TipoEncordaje.MANUAL,
            tensionMaxima = 56.5,
            tensionMinima = 23.9,
            turno_id = turno
        )
        tennisLabController.insertarMaquinaEncordado(maquinaEncordar!!)
        val maquinaaa = tennisLabController.findByTurnoId(maquinaEncordar!!._id)
        if (maquinaaa != null) {
            println("Maquina de Encordar creada correctamento $maquinaaa")
        }else {
            println("Error al crear maquina de encordar: $maquinaEncordar")
        }


        println("CREAR MAQUINA PERSONALIZAR")
        val turnoo = tennisLabController.findByTurnoId(turno!!._id)
        maquinaPersonalizar = MaquinaPersonalizar(
            marca = "Siemens",
            modelo = "Sparta",
            fechaAdquisicion = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
            numeroSerie = "20234",
            swingweight = "453",
            balance = 56.5,
            rigidez = 23.9,
            turno_id = turno
        )
        tennisLabController.insertarMaquinaPersonalizar(maquinaPersonalizar!!)
        val maquinaa = tennisLabController.findByMaquinaPersonalizarId(maquinaPersonalizar!!._id)
        if (maquinaa != null) {
            println("Maquina de Personalizar creada correctamento $maquinaa")
        }else {
            println("Error al crear maquina de personalizar: $maquinaEncordar")
        }

    }


    private suspend fun borrarMaquinas() {
        println("BORRAR MAQUINA ENCORDADORA")
        val maquinaa = tennisLabController.findByMaquinaEncordadoId(maquinaEncordar!!._id)
        if (maquinaa != null) {
            tennisLabController.borrarMaquinaEncordado(maquinaa._id)
            println("Maquina encordadora borrada correctamento $maquinaa")
        }else {
            println("No se encuentra la maquina encordadora con id 888 ")
        }


        println("BORRAR MAQUINA PERSONALIZADORA")
        val maquinaPersonalizadora = tennisLabController.findByMaquinaPersonalizarId(maquinaPersonalizar!!._id)
        if (maquinaPersonalizadora != null) {
            tennisLabController.borrarMaquinaPersonalizar(maquinaPersonalizadora._id)
            println("Maquina encordadora borrada correctamento $maquinaPersonalizadora")
        }else {
            println("No se encuentra la maquina encordadora con id 111 ")
        }

    }




    private suspend fun comprobarPedidoCliente() {
        println("COMPROBAR PEDIDOS CLIENTE")
        val lista = tennisLabController.obtenerTodosLosPedido().toList()
        println(lista)
        val list = lista.filter { it.usuario_id?._id.toString() == usuarioo._id.toString() }
        obtenerPedidos(list)
    }

    private suspend fun cancelarPedidoCliente() {
        println("BORRAR PEDIDOS CLIENTE")

        val lista = tennisLabController.obtenerTodosLosPedido().toList()
        val list = lista.filter { it.usuario_id?._id.toString() == usuarioo._id.toString() }
        val pedido = tennisLabController.findByPedidoId(list[0]._id)
        if (pedido == null) {
            println("PEDIDO INCORRECTO")
        }else{
            tennisLabController.borrarPedido(pedidoCliente._id)
            println("PEDIDO BORRADO")
        }
    }

    private fun obtenerPedidos(list: List<Pedido>){

        list.forEach {
            println("""
                    ${it.toString()}
                    """)
        }

    }



    private suspend fun realizarPedidoCliente(){

        val tarea1 = crearTareaEncordado()
        tennisLabController.insertarTareaEncordado(tarea1)
        Listados.tareasCreadasEncordar.add(tarea1)


        val tarea2 = crearTareaPersonalizar()
        println("Añadiendo TAREA DE PERSONALIZACIÓN $tarea2")
        tennisLabController.insertarTareaPersonalizar(tarea2)
        Listados.tareasCreadasPersonalizar.add(tarea2)



        val tarea3 = crearTareaProducto()
        println("Añadiendo PRODUCTO $tarea3")
        tennisLabController.insertarProducto(tarea3)
        val a = productoToDto(tarea3)
        Listados.productos.add(a)



        val pedido = crearPedido(Listados.tareasCreadasEncordar,Listados.tareasCreadasPersonalizar, Listados.productos)
        tennisLabController.insertarPedido(pedido)
        println("Pedido creado: $pedido")

        obtenerPedidos(tennisLabController.obtenerTodosLosPedido().toList())
        println("Saliendo de la creacion de tareas")

        Listados.tareasCreadasPersonalizar.clear()
        Listados.tareasCreadasEncordar.clear()
        Listados.productos.clear()





    }

    private fun crearPedido(tareasEncordar: MutableList<TareaEncordado>, tareasPersonalizar: MutableList<TareaPersonalizacion>, producto: MutableList<ProductoDto>) : Pedido{
        var precioo : Double = 0.0

        tareasEncordar.forEach {
            precioo = precioo + it.precio
        }

        tareasPersonalizar.forEach {
            precioo = precioo + it.precio

        }

        producto.forEach {
            precioo = precioo + it.precio

        }

        val pedido = Pedido(
                    estado = Pedido.TipoEstado.EN_PROCESO,
                    fechaEntrada = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
                    fechaSalidaProgramada = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
                    fechaEntrega =LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
                    precio = precioo,
                    usuario_id = usuarioo
        )

        return pedido

    }

   private fun crearTareaPersonalizar(): TareaPersonalizacion{
        val precio = 20.0
        val peso = 28.0
        val balance = 56.6
        val rigidez = 98.9


        val tareaPersonalizar = TareaPersonalizacion(
            rigidez = rigidez,
            peso = peso,
            balance = balance,
            precio = precio,
            pedido_id = pedidoCliente
        )

       return tareaPersonalizar

    }

    private fun crearTareaEncordado() : TareaEncordado {
        val precio : Double = 20.0
        val tensionVertical = 10.3
        val tensionHorizontal = 2.2
        val cordajeVertical = "Si"
        val cordajeHorizontal = "No"


        val tarea = TareaEncordado(
                    precio = precio,
                    tensionVertical = tensionVertical,
                    cordajeVertical = cordajeVertical,
                    tensionHorizontal = tensionHorizontal,
                    cordajeHorizontal = cordajeHorizontal,
                    nudos = TareaEncordado.NumeroNudos.CUATRO,
                    pedido_id = pedidoCliente
            )

        return tarea

    }


    private fun crearTareaProducto() : Producto {
        val producto =
            Producto(
                marca = "Babolat",
                modelo = "DAM",
                precio = 22.0,
                stock = "12",
                tipoProducto = Producto.TipoProducto.RAQUETA,
                pedido_id = pedidoCliente
            )
        return producto
    }


    private suspend fun consultarTodosPedidos(){
        println("TODOS LOS PEDIDOS")
        val list = tennisLabController.obtenerTodosLosPedido().toList()
        println(list)
        println("PEDIDOS COMPLETADOS")
        val listaCompletados = list.filter { it.estado == Pedido.TipoEstado.TERMINADO }.toList()
        listaCompletados.forEach {
            println(it)
            val a = pedidoToDto(it)
            Listados.pedidosCompletados.add(a)
        }
        println(listaCompletados)
        println("PEDIDOS PENDIENTES")
        val listadoPendientes = list.filter { it.estado == Pedido.TipoEstado.EN_PROCESO }.toList()
        listadoPendientes.forEach {
            val a = pedidoToDto(it)
            Listados.pedidosPendientes.add(a)
            println(it)
        }
        println(listadoPendientes)

        println("PRODUCTOS")
        val productos = tennisLabController.obtenerTodosLosProducto().toList()
        productos.forEach {
            println(it)
            val a = productoToDto(it)
            Listados.productos.add(a)
        }
        println(listadoPendientes)

        println("LISTADOS SERVICIOS")
        val listaServicios = tennisLabController.obtenerTodosLosTareaEncordado().toList()
        println(listaServicios)
        listaServicios.forEach {
            val pedido = it.pedido_id?.let { it1 -> tennisLabController.findByPedidoId(it1._id) }
            if (pedido != null){
                val tarea = TareaDto(
                    pedido.usuario_id.toString(),
                    it._id.toString(),
                    "Tarea Encordar",
                    pedido.estado.toString()
                )
                println(tarea.toString())
                tennisLabController.insertarTareaRest(tarea)
                Listados.servicios.add(tarea)
            }
        }

        val listaaServicios = tennisLabController.obtenerTodosLosTareaPersonalizar().toList()
        listaaServicios.forEach {
            val pedido = it.pedido_id?.let { it1 -> tennisLabController.findByPedidoId(it1._id) }
            if (pedido != null){
                val tarea = TareaDto(
                    pedido.usuario_id.toString(),
                    it._id.toString(),
                    "Tarea Personalizar",
                    pedido.estado.toString()
                )
                println(tarea.toString())
                tennisLabController.insertarTareaRest(tarea)
                Listados.servicios.add(tarea)
            }
        }

        println(("LISTADO DE ASIGNACIONES ENCORDADORES JSON"))

        val pedidos = tennisLabController.obtenerTodosLosPedido().toList()
        pedidos.forEach {
            var asignaciones = AsignacionesEncordadores(
                idPedido = it._id.toString(),
                encordador_id = it.usuario_id.toString(),
                fecha = LocalDate.now().toString(),
            )
            println(asignaciones.toString())
            Listados.asignaciones.add(asignaciones)
        }



    }

    private suspend fun consultarTodosTurnos(){
        println("TODOS LOS TURNOS")
        val list =  tennisLabController.obtenerTodosLosTurnos().toList()
        println(list)

    }

    private suspend fun consultarTodasMaquinas(){
        println("TODOS LAS MAQUINAS DE PERSONALIZAR")
        val list =  tennisLabController.obtenerTodosLosMaquinaPersonalizar().toList()
        println(list)

        println("TODOS LAS MAQUINAS DE ENCORDAR")
        val list1 =  tennisLabController.obtenerTodosLosMaquinaEncordado().toList()
        println(list1)

    }


    suspend fun listadoPedidosCompletados() = withContext(Dispatchers.IO) {
        println("LISTADOS PEDIDOS COMPLETADOS JSON")
        var listado = async {
            FicheroJsonPedidosCompletados().writeFichero(
                System.getProperty("user.dir") + File.separator + "Listas" + File.separator + "listadosPedidosCompletados.json",
                Listados.pedidosCompletados.toList()
            )
        }
        listado.await()
    }

    suspend fun listadoPedidosPendientes() = withContext(Dispatchers.IO) {
        println("LISTADOS PEDIDOS PENDIENTES JSON")
        var listado = async {
            FicheroJsonPedidosCompletados().writeFichero(
                System.getProperty("user.dir") + File.separator + "Listas" + File.separator + "listadosPedidosPendientes.json",
                Listados.pedidosPendientes.toList()
            )
        }
        listado.await()
    }


    suspend fun listadoAsignacionesEncordadores() = withContext(Dispatchers.IO) {

        println("LISTADOS ASGINACIONES ECORDADORES")
        var listado = async {
            FicheroJsonAsignaciones().writeFichero(
                System.getProperty("user.dir") + File.separator + "Listas" + File.separator + "listadosAsignacionesEncordadores.json",
                Listados.asignaciones.toList()
            )
        }

        listado.await()
    }


    suspend fun listadoProductos() = withContext(Dispatchers.IO) {
        println("LISTADOS PRODUCTOS")

        var listado = async {
            FicheroJsonProductos().writeFichero(
                System.getProperty("user.dir") + File.separator + "Listas" + File.separator + "listadosProductos.json",
                Listados.productos.toList()
            )
        }
        listado.await()
    }

    suspend fun listadoServicios() = withContext(Dispatchers.IO) {
        println("LISTADOS SERVICIOS")

        var listado = async {
            FicheroJsonServicios().writeFichero(
                System.getProperty("user.dir") + File.separator + "Listas" + File.separator + "listadosServicios.json",
                Listados.servicios.toList()
            )
        }
        listado.await()
    }



    suspend fun listasJson() = withContext(Dispatchers.IO) {
        if (!Files.isDirectory(Path.of(System.getProperty("user.dir") + File.separator + "Listas"))) {
            Files.createDirectories(Path.of(System.getProperty("user.dir") + File.separator + "Listas"))
        }

        println("")
        var productos = async {
            listadoProductos()
        }

        val servicios = async {
            listadoServicios()
        }
        var asignacionesEncordadores = async {
            listadoAsignacionesEncordadores()
        }
        var completados = async {
            listadoPedidosCompletados()
        }

        var pendientes = async {
            listadoPedidosPendientes()
        }
        productos.await()
        servicios.await()
        asignacionesEncordadores.await()
        completados.await()
        pendientes.await()
    }

}




