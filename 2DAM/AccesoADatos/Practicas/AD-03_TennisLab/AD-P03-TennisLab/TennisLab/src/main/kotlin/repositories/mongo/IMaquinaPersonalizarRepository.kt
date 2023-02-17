package repositories.mongo

import models.MaquinaPersonalizar

/**
 * Interface de la máquina personalizar repository
 *
 */
interface IMaquinaPersonalizarRepository : CrudRepository<MaquinaPersonalizar, String> {
}