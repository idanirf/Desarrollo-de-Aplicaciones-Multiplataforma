package es.drodriguez.com.tennislabspring.controller

import dto.TareaDto
import es.drodriguez.com.tennislabspring.models.*
import es.drodriguez.com.tennislabspring.repositories.*
import es.drodriguez.com.tennislabspring.utils.Contraseñas
import exceptions.UserControllerException
import exceptions.UserException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import mapper.databaseToModel
import mapper.toModel
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class TennisLabController
@Autowired constructor(
    private val userRepository: UserRepository,
    private val turnoRepository: TurnoRepository,
    private val tareaEncordadoRepository: TareaEncordadoRepository,
    private val tareaPersonalizarRepository: TareaPersonalizarRepository,
    private val productoRepository: ProductoRepository,
    private val pedidoRepository: PedidoRepository,
    private val maquinaEncordadoRepository: MaquinaEncordarRepository,
    private val maquinaPersonalizarRepository: MaquinaPersonalizarRepository,
    private val userRepositoryCache: UserRepositoryCache,
    private val restRepository: RestRepository,

    ){


    suspend fun getUsersByEmail(email: String, password: String) : User?{
        var user = findByEmail(email)
        if(user == null){
            throw UserControllerException("El usuario no exite")
        }else{
            if (user.password != Contraseñas.encriptar(password)){
                throw UserControllerException("Contraseña incorrecta")

            }
        }
        return user
    }


    suspend fun findByEmail(email: String): User? {
        val list = userRepository.findAll().toList()
        val enti = list.filter{ it.email == email }.firstOrNull()
        try {
            return enti
        }catch (e: Exception){
            throw UserException("Ha ocurrido un error al obtener el usuario con email: $email")
        }
    }

    suspend fun insertarUsuario(user: User){
        userRepository.save(user)
        userRepositoryCache.save(user)
    }


    suspend fun actualizarUsuario(user: User) {
        userRepository.save(user)
        userRepositoryCache.update(user)
    }

    suspend fun borrarUsuario(id: ObjectId) {
        userRepository.deleteById(id)
        userRepositoryCache.delete(id)
    }

    suspend fun findByUserId(_id: ObjectId) : User? {
        return userRepository.findById(_id)
    }

    suspend fun obtenerTodosLosUsuarios() : Flow<User> {
        return userRepository.findAll()
    }


    suspend fun resetUsers() {
        userRepository.deleteAll()
    }







    suspend fun insertarTurno(turno: Turno){
        turnoRepository.save(turno)
    }

    suspend fun actualizarTurno(turno: Turno) {
        turnoRepository.save(turno)
    }

    suspend fun borrarTurno(id: ObjectId) {
        turnoRepository.deleteById(id)
    }

    suspend fun findByTurnoId(_id: ObjectId) : Turno? {
        return turnoRepository.findById(_id)
    }

    suspend fun obtenerTodosLosTurnos() : Flow<Turno> {
        return turnoRepository.findAll()
    }




    suspend fun resetTurnos() {
        turnoRepository.deleteAll()
    }





    suspend fun insertarTareaEncordado(tareaEncordado: TareaEncordado){
        tareaEncordadoRepository.save(tareaEncordado)
    }

    suspend fun actualizarTareaEncordado(tareaEncordado: TareaEncordado) {
        tareaEncordadoRepository.save(tareaEncordado)
    }

    suspend fun borrarTareaEncordado(id: ObjectId) {
        tareaEncordadoRepository.deleteById(id)
    }

    suspend fun findByTareaEncordadoId(tareaEncordado: TareaEncordado){
        tareaEncordadoRepository.findById(tareaEncordado._id)
    }

    suspend fun obtenerTodosLosTareaEncordado() : Flow<TareaEncordado> {
        return tareaEncordadoRepository.findAll()
    }


    suspend fun resetTareaEncordado() {
        tareaEncordadoRepository.deleteAll()
    }




    suspend fun insertarTareaPersonalizar(tareaPersonalizar: TareaPersonalizacion){
        tareaPersonalizarRepository.save(tareaPersonalizar)
    }

    suspend fun actualizarTareaPersonalizar(tareaPersonalizar: TareaPersonalizacion) {
        tareaPersonalizarRepository.save(tareaPersonalizar)
    }

    suspend fun borrarTareaPersonalizar(id: ObjectId) {
        tareaPersonalizarRepository.deleteById(id)
    }

    suspend fun findByTareaPersonalizarId(tareaPersonalizar: TareaPersonalizacion){
        tareaPersonalizarRepository.findById(tareaPersonalizar._id)
    }

    suspend fun obtenerTodosLosTareaPersonalizar() : Flow<TareaPersonalizacion> {
       return tareaPersonalizarRepository.findAll()
    }


    suspend fun resetTareaPersonalizar() {
        tareaPersonalizarRepository.deleteAll()
    }







    suspend fun insertarProducto(producto: Producto){
        productoRepository.save(producto)
    }

    suspend fun actualizarProducto(producto: Producto) {
        productoRepository.save(producto)
    }

    suspend fun borrarProducto(id: ObjectId) {
        productoRepository.deleteById(id)
    }

    suspend fun findByProductoId(producto: Producto){
        productoRepository.findById(producto._id)
    }

    suspend fun obtenerTodosLosProducto() : Flow<Producto> {
        return productoRepository.findAll()
    }






    suspend fun resetProductos() {
        productoRepository.deleteAll()
    }



    suspend fun insertarPedido(pedido: Pedido){
        pedidoRepository.save(pedido)
    }

    suspend fun actualizarPedido(pedido: Pedido) {
        pedidoRepository.save(pedido)
    }

    suspend fun borrarPedido(id: ObjectId) {
        pedidoRepository.deleteById(id)
    }

    suspend fun findByPedidoId(_id: ObjectId) : Pedido?{
        return pedidoRepository.findById(_id)
    }

    suspend fun obtenerTodosLosPedido(): Flow<Pedido> {
       return pedidoRepository.findAll()
    }

    suspend fun resetPedidos() {
        pedidoRepository.deleteAll()
    }





    suspend fun insertarMaquinaEncordado(maquinaEncordado: MaquinaEncordar){
        maquinaEncordadoRepository.save(maquinaEncordado)
    }

    suspend fun actualizarMaquinaEncordado(maquinaEncordado: MaquinaEncordar) {
        maquinaEncordadoRepository.save(maquinaEncordado)
    }

    suspend fun borrarMaquinaEncordado(id: ObjectId) {
        maquinaEncordadoRepository.deleteById(id)
    }

    suspend fun findByMaquinaEncordadoId(_id: ObjectId) : MaquinaEncordar?{
        return maquinaEncordadoRepository.findById(_id)
    }

    suspend fun obtenerTodosLosMaquinaEncordado() : Flow<MaquinaEncordar> {
        return maquinaEncordadoRepository.findAll()
    }



    suspend fun resetMaquinasEncordar() {
        maquinaEncordadoRepository.deleteAll()
    }










    suspend fun insertarMaquinaPersonalizar(maquinaPersonalizar: MaquinaPersonalizar){
        maquinaPersonalizarRepository.save(maquinaPersonalizar)
    }

    suspend fun actualizarMaquinaPersonalizar(maquinaPersonalizar: MaquinaPersonalizar) {
        maquinaPersonalizarRepository.save(maquinaPersonalizar)
    }

    suspend fun borrarMaquinaPersonalizar(id: ObjectId) {
        maquinaPersonalizarRepository.deleteById(id)
    }

    suspend fun findByMaquinaPersonalizarId(_id: ObjectId) : MaquinaPersonalizar?{
       return maquinaPersonalizarRepository.findById(_id)
    }

    suspend fun obtenerTodosLosMaquinaPersonalizar() : Flow<MaquinaPersonalizar> {
       return maquinaPersonalizarRepository.findAll()
    }

    suspend fun resetMaquinaPersonalizar() {
        maquinaPersonalizarRepository.deleteAll()
    }



    //-------------------------------------------------REST--------------------------------------------------

    suspend fun getAllApiUsers() : Flow<User> {
        val flujo = mutableListOf<User>()
        val list =  restRepository.findAll().toList()
        list.forEach {
            val user = toModel(it)
            flujo.add(user)

        }
        return flujo.asFlow()
    }

    suspend fun getAllApiTareas() : Flow<TareaDto> {
        return restRepository.findAllTareas()

    }


    suspend fun insertarTareaRest(tarea: TareaDto){
        restRepository.create(tarea)
    }
}
