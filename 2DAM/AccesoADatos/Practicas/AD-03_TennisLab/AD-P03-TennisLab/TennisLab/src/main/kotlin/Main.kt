import data.Data
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import models.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.GlobalContext.startKoin
import org.koin.ksp.generated.defaultModule
import repositories.cache.UserRepositoryCache
import repositories.mongo.*
import services.cache.CacheClient
import services.mongo.MongoDbManager
import view.TennisLabView

    val turnoRepository = TurnoRepository()
    val userRepository = UserRepository()
    val cached = UserRepositoryCache(CacheClient())
    val pedidoRepository = PedidoRepository()
    val tareaPersonalizacionRepository = TareaPersonalizacionRepository()
    val tareaEncordadoRepository = TareaEncordadoRepository()
    val maquinaPersonalizar = MaquinaPersonalizarRepository()
    val maquinaEncordar = MaquinaEncordarRepository()
    val productoRepository = ProductoRepository()

fun main() = runBlocking {
    startKoin {
        printLogger()

        modules(
            defaultModule
        )


    }

    KoinApp().run()

}

class KoinApp : KoinComponent {
    private val controller: TennisLabView by inject()

    suspend fun run() {
        runBlocking {
            launch(Dispatchers.IO) {
                controller()
            }
        }
        }

        private suspend fun controller(){

            limpiarDatos()


            val listUsuarios = Data.getUsers().asFlow()
            listUsuarios.collect { userRepository.save(it) }

            val listPedidos = Data.getPedidos().asFlow()
            listPedidos.collect { pedidoRepository.save(it) }

            val listTareaEncordado = Data.getTareaEncordado().asFlow()
            listTareaEncordado.collect { tareaEncordadoRepository.save(it) }

            val listTareaPersonalizacion = Data.getTareasPersonalizacion().asFlow()
            listTareaPersonalizacion.collect { tareaPersonalizacionRepository.save(it) }

            val listProductos = Data.getProducto().asFlow()
            listProductos.collect { productoRepository.save(it) }

            val listTurnos = Data.getTurnos().asFlow()
            listTurnos.collect { turnoRepository.save(it) }

            val listMaquinaEncordado = Data.getMaquinaEncordado().asFlow()
            listMaquinaEncordado.collect { maquinaEncordar.save(it) }

            val listMaquinaPersonalizacion = Data.getMaquinaPersonalizacion().asFlow()
            listMaquinaPersonalizacion.collect { maquinaPersonalizar.save(it) }

            controller.login()
        }



    }



suspend fun limpiarDatos() = withContext(Dispatchers.IO) {
    if (MongoDbManager.database.getCollection<User>().estimatedDocumentCount() > 0) {
        MongoDbManager.database.getCollection<User>().drop()
    }
    if (MongoDbManager.database.getCollection<Turno>().estimatedDocumentCount() > 0) {
        MongoDbManager.database.getCollection<Turno>().drop()
    }

    if (MongoDbManager.database.getCollection<TareaEncordado>().estimatedDocumentCount() > 0) {
        MongoDbManager.database.getCollection<TareaEncordado>().drop()
    }

    if (MongoDbManager.database.getCollection<TareaPersonalizacion>().estimatedDocumentCount() > 0) {
        MongoDbManager.database.getCollection<TareaPersonalizacion>().drop()
    }
    if (MongoDbManager.database.getCollection<MaquinaEncordar>().estimatedDocumentCount() > 0) {
        MongoDbManager.database.getCollection<MaquinaEncordar>().drop()
    }
    if (MongoDbManager.database.getCollection<MaquinaPersonalizar>().estimatedDocumentCount() > 0) {
        MongoDbManager.database.getCollection<MaquinaPersonalizar>().drop()
    }
    if (MongoDbManager.database.getCollection<Pedido>().estimatedDocumentCount() > 0) {
        MongoDbManager.database.getCollection<Pedido>().drop()
    }

    if (MongoDbManager.database.getCollection<Producto>().estimatedDocumentCount() > 0) {
        MongoDbManager.database.getCollection<Producto>().drop()
    }



}
