package repositories

import models.Empleado
import org.litote.kmongo.Id

interface EmpleadosRepository: CrudRepository<Empleado, Id<Empleado>> {
}