package repositories.departamentos

import models.Departamento
import repositories.CrudRepository
import java.util.*

interface DepartamentoRepository : CrudRepository<Departamento, UUID>