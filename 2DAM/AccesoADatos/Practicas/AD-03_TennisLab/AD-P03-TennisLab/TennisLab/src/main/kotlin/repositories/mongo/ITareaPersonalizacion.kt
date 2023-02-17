package repositories.mongo

import models.TareaPersonalizacion

/**
 * Interfaz de la tarea personalización
 */
interface ITareaPersonalizacion: CrudRepository<TareaPersonalizacion, String> {
}