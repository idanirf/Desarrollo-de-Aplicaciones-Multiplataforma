package controllers

import models.Empleado
import org.koin.core.annotation.Single
import repositories.EmpleadoRepository

@Single
class EmpleadoController(val empleadoRepository: EmpleadoRepository) {

    fun getAllEmpleados() {
        val empleados = empleadoRepository.findAll()
        println(empleados)
    }

    fun getEmpleadosById(id: Int) {
        val empleado = empleadoRepository.findById(id)
        println(empleado)
    }

    fun createEmpleado() {
        val empleado = Empleado(1, "Juan", "Perez")
        empleadoRepository.create(empleado)
    }

    fun updateEmpleado() {
        val empleado = empleadoRepository.findById(1)
        empleadoRepository.update(empleado)
    }

    fun deleteEmpleado() {
        val empleado = empleadoRepository.findById(1)
        empleadoRepository.delete(empleado)
    }
}