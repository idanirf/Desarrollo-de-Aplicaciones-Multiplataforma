package repositories.owner

import models.Owner
import repositories.ICrudRepository

interface IOwnerRepository: ICrudRepository<Owner, String> {
}