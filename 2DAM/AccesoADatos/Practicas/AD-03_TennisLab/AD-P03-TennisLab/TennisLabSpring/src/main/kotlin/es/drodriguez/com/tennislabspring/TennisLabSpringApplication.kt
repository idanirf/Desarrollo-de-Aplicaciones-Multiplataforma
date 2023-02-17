package es.drodriguez.com.tennislabspring

import es.drodriguez.com.tennislabspring.controller.TennisLabController
import es.drodriguez.com.tennislabspring.data.Data
import es.drodriguez.com.tennislabspring.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import view.TennisLabView

@SpringBootApplication
class TennisLabSpringApplication

@Autowired constructor(private val controller: TennisLabController): CommandLineRunner {
    override fun run(vararg args: String?): Unit = runBlocking {
        runBlocking {
            launch(Dispatchers.IO) {
                controller.resetPedidos()
                controller.resetProductos()
                controller.resetTurnos()
                controller.resetUsers()
                controller.resetMaquinasEncordar()
                controller.resetMaquinaPersonalizar()
                controller.resetTareaEncordado()
                controller.resetTareaPersonalizar()

                val listUsuarios = Data.getUsers().asFlow()
                listUsuarios.collect { controller.insertarUsuario(it) }

                val listPedidos = Data.getPedidos().asFlow()
                listPedidos.collect { controller.insertarPedido(it) }

                val listTareaEncordado = Data.getTareaEncordado().asFlow()
                listTareaEncordado.collect { controller.insertarTareaEncordado(it) }

                val listTareaPersonalizacion = Data.getTareasPersonalizacion().asFlow()
                listTareaPersonalizacion.collect { controller.insertarTareaPersonalizar(it) }

                val listProductos = Data.getProducto().asFlow()
                listProductos.collect { controller.insertarProducto(it) }

                val listTurnos = Data.getTurnos().asFlow()
                listTurnos.collect { controller.insertarTurno(it) }

                val listMaquinaEncordado = Data.getMaquinaEncordado().asFlow()
                listMaquinaEncordado.collect { controller.insertarMaquinaEncordado(it) }

                val listMaquinaPersonalizacion = Data.getMaquinaPersonalizacion().asFlow()
                listMaquinaPersonalizacion.collect { controller.insertarMaquinaPersonalizar(it) }
            }
            val view = TennisLabView(controller)
            view.login()
        }
    }
}
fun main(args: Array<String>) {
    runApplication<TennisLabSpringApplication>(*args)

}
