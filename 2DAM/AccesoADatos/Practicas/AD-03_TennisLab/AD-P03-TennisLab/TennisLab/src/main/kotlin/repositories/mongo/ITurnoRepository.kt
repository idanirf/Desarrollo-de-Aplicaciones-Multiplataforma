package repositories.mongo

import models.Turno

/**
 * Interfaz del turno repository
 */
interface ITurnoRepository : CrudRepository<Turno, String> {
}