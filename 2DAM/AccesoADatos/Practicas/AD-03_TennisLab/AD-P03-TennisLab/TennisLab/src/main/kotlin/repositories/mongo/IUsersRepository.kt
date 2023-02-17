package repositories.mongo

import models.User
import org.litote.kmongo.Id

/**
 * Interfaz del users repository
 */
interface IUsersRepository : CrudRepository<User, String> {}
