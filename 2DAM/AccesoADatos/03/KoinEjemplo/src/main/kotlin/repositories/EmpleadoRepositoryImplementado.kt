package repositories

import models.Empleado
import org.koin.core.annotation.Single

@Single
class EmpleadoRepositoryImplementado: EmpleadoRepository{
    override fun create(entity: Empleado): Empleado {
        return Empleado(1, "Juan", "Perez")
    }

    override fun update(entity: Empleado): Empleado {
        return Empleado(1, "Juan", "Perez")

    }

    override fun delete(entity: Empleado) {
        return println("Empleado eliminado")

    }

    override fun findById(id: Int): Empleado {
    return Empleado(1, "Juan", "Perez")
    }

    override fun findAll(): List<Empleado> {
        return listOf(
            Empleado(1, "Juan", "Perez"),
            Empleado(2, "Maria", "Perez"))
    }
}