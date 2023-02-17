package repositories.mongo

import models.TareaEncordado

/**
 * Interfaz de la tarea encordado
 *
 */
interface ITareaEncordado: CrudRepository<TareaEncordado, String> {
}