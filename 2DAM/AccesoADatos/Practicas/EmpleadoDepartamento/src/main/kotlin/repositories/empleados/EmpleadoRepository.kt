package repositories.empleados

import models.Empleado
import repositories.CrudRepository
import java.util.*

interface EmpleadoRepository : CrudRepository<Empleado, UUID>