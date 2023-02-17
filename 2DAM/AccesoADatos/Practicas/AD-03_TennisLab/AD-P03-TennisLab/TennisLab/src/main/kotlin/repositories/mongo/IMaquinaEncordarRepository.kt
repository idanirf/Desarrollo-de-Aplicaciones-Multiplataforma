package repositories.mongo

import models.MaquinaEncordar

/**
 * Interface de la máquina encordar repository:
 */
interface IMaquinaEncordarRepository : CrudRepository<MaquinaEncordar, String> {
}