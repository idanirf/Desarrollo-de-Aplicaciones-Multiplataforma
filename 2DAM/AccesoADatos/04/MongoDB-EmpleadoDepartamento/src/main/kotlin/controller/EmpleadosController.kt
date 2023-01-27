package controller

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import models.Empleado
import org.litote.kmongo.Id
import repositories.EmpleadosRepository
import services.EmpleadosService

class EmpleadosController(
    private val empleadosService: EmpleadosService,
    private val empleadosRepository: EmpleadosRepository
) {
    fun getEmpleados(): Flow<Empleado> {
        return empleadosRepository.findAll()
            .flowOn(Dispatchers.IO)
    }

    suspend fun newEmpleado(empleado: Empleado): Empleado {
         empleadosRepository.save(empleado)
            return empleado
    }

    suspend fun getEmpleadoById(id: Id<Empleado>): Empleado? {
        return empleadosRepository.findById(id)
    }

    suspend fun updateEmpleado(empleado: Empleado) {
        empleadosRepository.save(empleado)
    }

    suspend fun deleteEmpleado(empleado: Empleado): Boolean {
        return empleadosRepository.delete(empleado)
    }
}