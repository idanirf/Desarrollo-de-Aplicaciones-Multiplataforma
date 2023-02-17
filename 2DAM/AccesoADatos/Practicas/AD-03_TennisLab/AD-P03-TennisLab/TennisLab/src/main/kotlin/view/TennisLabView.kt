package view

import controllers.*
import data.Data
import data.Listados
import dto.TareaDto
import exceptions.TennisLabControllerException
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.toList
import mapper.toModel
import models.*
import models.AsignacionesEncordadores
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import org.litote.kmongo.newId
import utils.Contraseñas
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
@Single
class TennisLabView(
    @Named("TennisLabController") private var tennisLabController: TennisLabController
) {
   private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private lateinit var usuarioo : User
    private var pedido :Pedido? = null
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
                        User.TipoUsuario.CLIENTE -> Pedido.TipoEstado.RECIBIDO
                        User.TipoUsuario.TRABAJADOR -> Pedido.TipoEstado.EN_PROCESO
                        User.TipoUsuario.ADMIN -> Pedido.TipoEstado.EN_PROCESO
                    },
                    fechaEntrada = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
                    fechaSalidaProgramada = LocalDateTime.parse("2023-08-27T00:00:00.000+05:30", Data.formatter),
                    fechaEntrega = LocalDateTime.parse("2023-12-27T00:00:00.000+05:30", Data.formatter),
                    precio = 34.3,
                    usuario_id = usuarioo._id
                )
                tennisLabController.añadirPedidos(pedidoCliente)

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
        val list = tennisLabController.findAllUsersApi().toList()
        list.forEach {
            val u = toModel(it)
            println("Insetando usuarios")
            tennisLabController.añadirUser(u)
            println(u)
        }

        val listaTareas = tennisLabController.findAllTareasApi().toList()
        listaTareas.forEach {
            Listados.servicios.add(it)
        }
    }


    suspend fun accionesCliente(){
        var pedidoCliente = Pedido(
            _id = "123456789",
            estado = Pedido.TipoEstado.RECIBIDO,
            fechaEntrada = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
            fechaSalidaProgramada = LocalDateTime.parse("2023-08-27T00:00:00.000+05:30", Data.formatter),
            fechaEntrega = LocalDateTime.parse("2023-12-27T00:00:00.000+05:30", Data.formatter),
            precio = 34.3,
            usuario_id = usuarioo!!._id
        )
        tennisLabController.añadirPedidos(pedidoCliente)
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


    private suspend fun crearUsuario() {
        println("CREAR USUARIO ADMIN")
        val user = User(
            _id = "555",
            id = "557",
            name = "pepe",
            username = "pepe345",
            email = "pepe@gmail.com",
            password = Contraseñas.encriptar("pepereina"),
            tipoUser = User.TipoUsuario.CLIENTE,
            phone = "665142892",
            website = "http://www.pepe.com",

            )
        tennisLabController.añadirUser(user)
        val userr = tennisLabController.getUsersById(user._id)
        if (userr != null) {
            println("User creado correctamento $userr")
        }else {
            println("Error al crear user: $userr")
        }
    }


    private suspend fun borrarUsuario() {
        println("BORRAR USUARIO")
        val user = tennisLabController.getUsersById("555")
        if (user != null) {
            tennisLabController.deleteUser(user._id)
            println("Usuario borrado correctamento $user")
        }else {
            println("No se encuentra el user con id 555 ")
        }

    }
    private suspend fun crearTurnos() {
        println("CREAR TURNO ADMIN")
        val turno = Turno(
            _id = "999",
            fechaInicio = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
            fechaFinal = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
            usuario_id = usuarioo!!._id
        )
        tennisLabController.añadirTurnos(turno)
        val turnoo = tennisLabController.getTurnosById(turno._id)
        if (turnoo != null) {
            println("Turno creado correctamento $turno")
        }else {
            println("Error al crear turno: $turno")
        }
    }

    private suspend fun borrarTurnos() {
        println("BORRAR TURNO ADMIN")
        val turnoo = tennisLabController.getTurnosById("999")
        if (turnoo != null) {
            tennisLabController.deleteTurnos(turnoo._id)
            println("Turno borrado correctamento $turnoo")
        }else {
            println("No se encuentra el turno con id 999 ")
        }

    }

    private suspend fun crearMaquinas() {
        println("CREAR MAQUINA ENCORDAR")
        val turno = tennisLabController.getTurnosById("999")
        val maquinaEncordar = MaquinaEncordar(
            _id = "888",
            marca = "Siemens",
            modelo = "Sparta",
            fechaAdquisicion = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
            numeroSerie = "20234",
            tipo = MaquinaEncordar.TipoEncordaje.MANUAL,
            tensionMaxima = 56.5,
            tensionMinima = 23.9,
            turno_id = turno?._id
        )
        tennisLabController.añadirMaquinaEncordar(maquinaEncordar)
        val maquinaaa = tennisLabController.getMaquinaEncordarById(maquinaEncordar._id)
        if (maquinaaa != null) {
            println("Maquina de Encordar creada correctamento $maquinaaa")
        }else {
            println("Error al crear maquina de encordar: $maquinaEncordar")
        }


        println("CREAR MAQUINA PERSONALIZAR")
        val turnoo = tennisLabController.getTurnosById("999")
        val maquinaPersonalizar = MaquinaPersonalizar(
            _id = "111",
            marca = "Siemens",
            modelo = "Sparta",
            fechaAdquisicion = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
            numeroSerie = "20234",
            swingweight = "453",
            balance = 56.5,
            rigidez = 23.9,
            turno_id = turno?._id
        )
        tennisLabController.añadirMaquinaPersonalizacion(maquinaPersonalizar)
        val maquinaa = tennisLabController.getMaquinaPersonalizacionById(maquinaPersonalizar._id)
        if (maquinaa != null) {
            println("Maquina de Personalizar creada correctamento $maquinaa")
        }else {
            println("Error al crear maquina de personalizar: $maquinaEncordar")
        }

    }


    private suspend fun borrarMaquinas() {
        println("BORRAR MAQUINA ENCORDADORA")
        val maquinaa = tennisLabController.getMaquinaEncordarById("888")
        if (maquinaa != null) {
            tennisLabController.deleteMaquinaEncordar(maquinaa._id)
            println("Maquina encordadora borrada correctamento $maquinaa")
        }else {
            println("No se encuentra la maquina encordadora con id 888 ")
        }


        println("BORRAR MAQUINA PERSONALIZADORA")
        val maquinaPersonalizadora = tennisLabController.getMaquinaPersonalizacionById("111")
        if (maquinaPersonalizadora != null) {
            tennisLabController.deleteMaquinaPersonalizacion(maquinaPersonalizadora._id)
            println("Maquina encordadora borrada correctamento $maquinaPersonalizadora")
        }else {
            println("No se encuentra la maquina encordadora con id 111 ")
        }

    }




    private suspend fun comprobarPedidoCliente() {
        println("COMPROBAR PEDIDOS CLIENTE")
        val lista = tennisLabController.findAllPedidos().toList()
        val list = lista.filter { it.usuario_id == usuarioo!!._id }
        obtenerPedidos(list)
    }

    private suspend fun cancelarPedidoCliente() {
        val lista = tennisLabController.findAllPedidos().toList()
        val list = lista.filter { it.usuario_id == usuarioo!!._id }
        val pedido = tennisLabController.getPedidoById(list[1]._id)
        if (pedido == null) {
            println("PEDIDO INCORRECTO")
        }else{
            tennisLabController.deletePedido(pedidoCliente._id)
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
        tennisLabController.añadirTareasEncordado(tarea1)
        Listados.tareasCreadasEncordar.add(tarea1)


        val tarea2 = crearTareaPersonalizar()
        println("Añadiendo TAREA DE PERSONALIZACIÓN $tarea2")
        tennisLabController.añadirTareasPersonalizacion(tarea2)
        Listados.tareasCreadasPersonalizar.add(tarea2)



        val tarea3 = crearTareaProducto()
        println("Añadiendo PRODUCTO $tarea3")
        tennisLabController.añadirProducto(tarea3)
        Listados.productos.add(tarea3)



        val pedido = crearPedido(Listados.tareasCreadasEncordar,Listados.tareasCreadasPersonalizar, Listados.productos)
        tennisLabController.añadirPedidos(pedido)
        println("Pedido creado: $pedido")

        obtenerPedidos(tennisLabController.findAllPedidos().toList())
        println("Saliendo de la creacion de tareas")

        Listados.tareasCreadasPersonalizar.clear()
        Listados.tareasCreadasEncordar.clear()
        Listados.productos.clear()





    }

    private fun crearPedido(tareasEncordar: MutableList<TareaEncordado>, tareasPersonalizar: MutableList<TareaPersonalizacion>, producto: MutableList<Producto>) : Pedido{
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
                    _id = newId<Pedido>().toString(),
                    estado = Pedido.TipoEstado.RECIBIDO,
                    fechaEntrada = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
                    fechaSalidaProgramada = LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
                    fechaEntrega =LocalDateTime.parse("2022-12-27T00:00:00.000+05:30", Data.formatter),
                    precio = precioo,
                    usuario_id = usuarioo!!._id
        )

        return pedido

    }

   private fun crearTareaPersonalizar(): TareaPersonalizacion{
        val precio = 20.0
        val peso = 28.0
        val balance = 56.6
        val rigidez = 98.9


        val tareaPersonalizar = TareaPersonalizacion(
            _id = newId<TareaPersonalizacion>().toString(),
            rigidez = rigidez,
            peso = peso,
            balance = balance,
            precio = precio,
            pedido_id = "12345678910")

       return tareaPersonalizar

    }

    private fun crearTareaEncordado() : TareaEncordado {
        val precio : Double = 20.0
        val tensionVertical = 10.3
        val tensionHorizontal = 2.2
        val cordajeVertical = "Si"
        val cordajeHorizontal = "No"


        val tarea = TareaEncordado(
                    _id = newId<TareaEncordado>().toString(),
                    precio = precio,
                    tensionVertical = tensionVertical,
                    cordajeVertical = cordajeVertical,
                    tensionHorizontal = tensionHorizontal,
                    cordajeHorizontal = cordajeHorizontal,
                    nudos = TareaEncordado.NumeroNudos.CUATRO,
                    pedido_id = "12345678910"
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
                pedido_id = pedidoCliente._id
            )
        return producto
    }


    private suspend fun consultarTodosPedidos(){
        println("TODOS LOS PEDIDOS")
        val list = tennisLabController.findAllPedidos().toList()
        println(list)
        println("PEDIDOS COMPLETADOS")
        val listaCompletados = list.filter { it.estado == Pedido.TipoEstado.TERMINADO }.toList()
        listaCompletados.forEach {
            println(it)
            Listados.pedidosCompletados.add(it)
        }
        println(listaCompletados)
        println("PEDIDOS PENDIENTES")
        val listadoPendientes = list.filter { it.estado == Pedido.TipoEstado.EN_PROCESO }.toList()
        listadoPendientes.forEach {
            Listados.pedidosPendientes.add(it)
            println(it)
        }
        println(listadoPendientes)

        println("PRODUCTOS")
        val productos = tennisLabController.findAllProducto().toList()
        productos.forEach {
            println(it)
            Listados.productos.add(it)
        }
        println(listadoPendientes)

        println("LISTADOS SERVICIOS")
        val listaServicios = tennisLabController.findAllTareaEncordar().toList()
        println(listaServicios)
        listaServicios.forEach {
            val pedido = it.pedido_id?.let { it1 -> tennisLabController.getPedidoById(it1) }
            if (pedido != null){
                val tarea = TareaDto(
                    pedido.usuario_id,
                    it._id,
                    "Tarea Encordar",
                    pedido.estado.toString()
                )
                println(tarea.toString())
                Listados.servicios.add(tarea)
            }
        }

        val listaaServicios = tennisLabController.findAllTareaPersonalizar().toList()
        listaaServicios.forEach {
            val pedido = tennisLabController.getPedidoById(it.pedido_id!!)
            if (pedido != null){
                val tarea = TareaDto(
                    pedido.usuario_id,
                    it._id,
                    "Tarea Personalizar",
                    pedido.estado.toString()
                )
                println(tarea.toString())
                Listados.servicios.add(tarea)
            }
        }

        println(("LISTADO DE ASIGNACIONES ENCORDADORES JSON"))

        val pedidos = tennisLabController.findAllPedidos().toList()
        pedidos.forEach {
            var asignaciones = AsignacionesEncordadores(
                idPedido = it._id,
                encordador_id = it.usuario_id,
                fecha = LocalDate.now().toString(),
            )
            println(asignaciones.toString())
            Listados.asignaciones.add(asignaciones)
        }



    }

    private suspend fun consultarTodosTurnos(){
        println("TODOS LOS TURNOS")
        val list =  tennisLabController.findAllTurnos().toList()
        println(list)

    }

    private suspend fun consultarTodasMaquinas(){
        println("TODOS LAS MAQUINAS DE PERSONALIZAR")
        val list =  tennisLabController.findAllMaquinaPersonalizar().toList()
        println(list)

        println("TODOS LAS MAQUINAS DE ENCORDAR")
        val list1 =  tennisLabController.findAllMaquinaEncordar().toList()
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















