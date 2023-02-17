package controllers

import dto.TareaDto
import dto.UserDto
import exceptions.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import models.*
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import repositories.cache.UserRepositoryCache
import repositories.ktorfit.KtorfitRepository
import repositories.mongo.*
import utils.Contraseñas

@Single
@Named("TennisLabController")
class TennisLabController(
    @Named("UserRepository") private var userRepository: UserRepository,
    @Named("UserRepositoryCache") private var userRepositoryCache: UserRepositoryCache,
    @Named("TurnoRepository") private var turnoRepository: TurnoRepository,
    @Named("TareaPersonalizacionRepository") private var tareaPersonalizarRepository : TareaPersonalizacionRepository,
    @Named("TareaEncordadoRepository") private var tareaEncordarRepository : TareaEncordadoRepository,
    @Named("PedidoRepository") private val pedidoRepository: PedidoRepository,
    @Named("KtorfitRepository") private val ktorfitRepository : KtorfitRepository,
    @Named("ProductoRepository") private val productoRepository: ProductoRepository,
    @Named("MaquinaPersonalizarRepository") private var maquinaPersonalizarRepository : MaquinaPersonalizarRepository,
    @Named("MaquinaEncordarRepository") private var maquinaEncordarRepository : MaquinaEncordarRepository,
) {

    //------------------------------------USERS-------------------------------------------------------

    suspend fun getUsersByEmail(email: String, password: String) : User?{
        var user = userRepository.findByEmail(email)
        if(user == null){
            throw UserControllerException("El usuario no exite")
        }else{
            if (user.password != Contraseñas.encriptar(password)){
                throw UserControllerException("Contraseña incorrecta")

            }
        }
        return user
    }

    suspend fun getUsersById(id: String) : User?{
        val userCache = userRepositoryCache.findById(id)
        if(userCache == null) {
            var user = userRepository.findById(id)
            if (user == null) {
                throw UserControllerException("El usuario no existe")
            }
            return user
        }else{
            return userCache
        }
    }

    suspend fun añadirUser(entity: User){
        var usuario = userRepository.findByEmail(entity.email)
        if(usuario != null){
            throw UserControllerException("Ya hay un usuario con ese email")
        }else{
            userRepository.save(entity)
            userRepositoryCache.save(entity)
        }
    }

    suspend fun findAllUsers() : Flow<User> {
        val listUsuarios = userRepositoryCache.findAll()
        if (listUsuarios == null){
            return userRepository.findAll().flowOn(Dispatchers.IO)
        }else{
            return listUsuarios
        }

    }

    suspend fun updateUser(entity: User){
        userRepositoryCache.update(entity)
        userRepository.update(entity)
    }


    suspend fun deleteUser(_id: String){
        userRepository.delete(_id)
        userRepositoryCache.delete(_id)
    }








    //---------------------------------------------------TURNOS------------------------------------------------



    suspend fun findAllTurnos() : Flow<Turno> {
        return turnoRepository.findAll().flowOn(Dispatchers.IO)
    }


    suspend fun getTurnosById(id: String) : Turno?{
        var user = turnoRepository.findById(id)
        if (user == null) {
            throw TurnoControllersException("El turno no existe")
        }
        return user
    }

    suspend fun añadirTurnos(entity: Turno){
        turnoRepository.save(entity)
    }

    suspend fun actualizarTurnos(entity: Turno){
        turnoRepository.update(entity)
    }


    suspend fun deleteTurnos(_id: String){
        turnoRepository.delete(_id)
    }


    //---------------------------------------------------TAREAS------------------------------------------------





    suspend fun findAllTareaPersonalizar() : Flow<TareaPersonalizacion>{

        return tareaPersonalizarRepository.findAll().flowOn(Dispatchers.IO)
    }


    suspend fun getTareasPersonalizacionById(id: String) : TareaPersonalizacion?{

        var tarea = tareaPersonalizarRepository.findById(id)
        if (tarea == null) {
            throw TareaPersonalizacionControllerException("La tarea de personalizacion no existe")
        }else{
            return tarea
        }
    }

    suspend fun añadirTareasPersonalizacion(entity: TareaPersonalizacion){
        val pedido = pedidoRepository.findById(entity.pedido_id!!)
        if (pedido != null) {
            val tarea = TareaDto(
                userId = pedido.usuario_id,
                id = entity._id,
                title = "Tarea Personalizacio",
                completed = pedido.estado.toString()
            )
            ktorfitRepository.create(tarea)
        }
        tareaPersonalizarRepository.save(entity)
    }

    suspend fun actualizarTareasPersonalizacion(entity: TareaPersonalizacion){
        tareaPersonalizarRepository.update(entity)
    }

    suspend fun deleteTareasPersonalizacion(_id: String){
        tareaPersonalizarRepository.delete(_id)
    }

    suspend fun findAllTareaEncordar() : Flow<TareaEncordado>{

        return tareaEncordarRepository.findAll().flowOn(Dispatchers.IO)
    }

    suspend fun getTareasEncordarById(id: String) : TareaEncordado?{

        var tarea = tareaEncordarRepository.findById(id)
        if (tarea == null) {
            throw TareaEncordadoControllerException("La tarea de encordado no existe")
        }
        return tarea

    }


    suspend fun añadirTareasEncordado(entity: TareaEncordado){
        val pedido = pedidoRepository.findById(entity.pedido_id!!)
        if (pedido != null) {
            val tarea = TareaDto(
                userId = pedido.usuario_id,
                id = entity._id,
                title = "Tarea de Encordado",
                completed = pedido.estado.toString()
            )
            ktorfitRepository.create(tarea)
            println("POST -> TAREA SUBIDA")
        }
        tareaEncordarRepository.save(entity)
    }

    suspend fun actualizarTareasEncordado(entity: TareaEncordado){
        tareaEncordarRepository.update(entity)
    }


    suspend fun deleteTareasEncordadp(_id: String){
        tareaEncordarRepository.delete(_id)
    }

//-----------------------------------------------------PRODUCTOS------------------------------------------



    suspend fun findAllProducto() : Flow<Producto>{
        return productoRepository.findAll().flowOn(Dispatchers.IO)
    }


    suspend fun getProductoById(id: String) : Producto?{
        var producto = productoRepository.findById(id)
        if (producto == null) {
            throw ProductoControllerException("El producto no existe")
        }
        return producto

    }

    suspend fun añadirProducto(entity: Producto){
        productoRepository.save(entity)
    }

    suspend fun actualizarProducto(entity: Producto){
        productoRepository.update(entity)
    }


    suspend fun deleteProducto(id: String){
        productoRepository.delete(id)
    }




    //-----------------------------------------------PEDIDOS----------------------------------------------



    suspend fun findAllPedidos() : Flow<Pedido>{
        return pedidoRepository.findAll().flowOn(Dispatchers.IO)
    }


    suspend fun getPedidoById(id: String) : Pedido?{
        var pedido = pedidoRepository.findById(id)
        if (pedido == null) {
            throw PedidoControllerException("El pedido no existe")
        }
        return pedido

    }

    suspend fun añadirPedidos(entity: Pedido){
        pedidoRepository.save(entity)
    }

    suspend fun actualizarPedido(entity: Pedido){
        pedidoRepository.update(entity)
    }


    suspend fun deletePedido(id: String){
        pedidoRepository.delete(id)
    }




    //------------------------------------------------MAQUINAS------------------------------------------------




    suspend fun findAllMaquinaPersonalizar() : Flow<MaquinaPersonalizar>{
        return maquinaPersonalizarRepository.findAll().flowOn(Dispatchers.IO)
    }


    suspend fun getMaquinaPersonalizacionById(id: String) : MaquinaPersonalizar?{
        var maquina = maquinaPersonalizarRepository.findById(id)
        if (maquina == null) {
            throw MaquinaPersonalizarControllerException("La maquina de personalizacion no existe con ese id")
        }
        return maquina

    }

    suspend fun añadirMaquinaPersonalizacion(entity: MaquinaPersonalizar){
        maquinaPersonalizarRepository.save(entity)
    }

    suspend fun actualizarMaquinaPersonalizacion(entity: MaquinaPersonalizar){
        maquinaPersonalizarRepository.update(entity)
    }


    suspend fun deleteMaquinaPersonalizacion(_id: String){
        maquinaPersonalizarRepository.delete(_id)
    }



    suspend fun findAllMaquinaEncordar() : Flow<MaquinaEncordar>{
        return maquinaEncordarRepository.findAll().flowOn(Dispatchers.IO)

    }


    suspend fun getMaquinaEncordarById(id: String) : MaquinaEncordar?{

        var maquina = maquinaEncordarRepository.findById(id)
        if (maquina == null) {
            throw MaquinaEncordarControllerException("La maquina de encordar no existe con ese id")
        }
        return maquina
    }

    suspend fun añadirMaquinaEncordar(entity: MaquinaEncordar){
        maquinaEncordarRepository.save(entity)
    }

    suspend fun actualizarMaquinaEncordar(entity: MaquinaEncordar){
        maquinaEncordarRepository.update(entity)
    }


    suspend fun deleteMaquinaEncordar(_id: String){
        maquinaEncordarRepository.delete(_id)
    }





    //------------------------------------------------API REST ----------------------------------------------

    suspend fun findAllUsersApi() : Flow<UserDto>{
        return ktorfitRepository.findAll().flowOn(Dispatchers.IO)
    }

    suspend fun findAllTareasApi() : Flow<TareaDto>{
        return ktorfitRepository.findAllTareas().flowOn(Dispatchers.IO)
    }


    suspend fun añadirTareaApi(entity: TareaDto){
        ktorfitRepository.create(entity)
    }


    suspend fun actualizarTareaApi(tarea: TareaDto) {
        ktorfitRepository.update(tarea)
    }

    suspend fun deleteTareaApi(id: String) {
        ktorfitRepository.delete(id)
    }

}