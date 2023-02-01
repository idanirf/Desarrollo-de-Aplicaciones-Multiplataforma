import controllers.EmpleadoController
import models.Empleado
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject
import org.koin.ksp.generated.defaultModule

fun main() {
    startKoin{
        defaultModule()
    }
    Runner().run()
}

class Runner: KoinComponent {
    fun run() {
        val controller: EmpleadoController by inject()
        val p = Empleado(id = 1, nombre = "Daniel", apellido="Rodriguez Fernandez")
        controller.createEmpleado()
        controller.getAllEmpleados()
        controller.getEmpleadosById(1)
        controller.updateEmpleado()
        controller.deleteEmpleado()
        println("¡FIN!")
    }
}