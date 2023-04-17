import controller.EmpleadosController
import db.MongoDbManager
import db.loadDataDepartamentos
import db.loadDataEmpleados
import kotlinx.coroutines.*
import kotlinx.coroutines.withContext
import models.Empleado
import repositories.EmpleadosRepositoryImplementacion
import services.EmpleadosService

fun main(): Unit = runBlocking {
    println("EJEMPLO DE EMPLEADOS Y DEPARTAMENTOS")
    val limpiar = launch {
     limpiarDatos()
    }
    limpiar.join()

    val controller = EmpleadosController(EmpleadosService(), EmpleadosRepositoryImplementacion())
    val empleados = mutableListOf<Empleado>()

    val init = launch {
        val departamentosInit = loadDataDepartamentos()
        val empleadosInit = loadDataEmpleados()
        empleadosInit[0].departamento = departamentosInit[0]
        empleadosInit[1].departamento = departamentosInit[1]
        empleadosInit.forEach { empleado ->
            controller.newEmpleado(empleado)
        }
        empleados.clear()
        controller.getEmpleados().collect {
            empleados.add(it)
        }
        empleados.forEach { empleado ->
            println(empleado)
        }
    }
    init.join()

    delay(5000L)

    val update = launch {
        val empleado = controller.getEmpleadoById(empleados[0].id)
        empleado?.let {
            println(it)
        }
        empleado?.let {
            it.apellidos = "Fernández"
            controller.updateEmpleado(it)
        }
        controller.getEmpleadoById(empleados[0].id)?.let {
            println(it)
        }

        // Borrar al empleado Juan
        val del = controller.getEmpleadoById(empleados[0].id)
        del?.let {
            if (controller.deleteEmpleado(it)) {
                println("Empleado borrado")
            }
        }
        empleados.clear()
        controller.getEmpleados().collect {
            empleados.add(it)
        }
    }
    update.join()

    delay(5000L)



}

suspend fun limpiarDatos() = withContext(Dispatchers.IO) {
    if (MongoDbManager.database.getCollection<Empleado>().estimatedDocumentCount() > 0) {
        MongoDbManager.database.getCollection<Empleado>().drop()
    }

}
