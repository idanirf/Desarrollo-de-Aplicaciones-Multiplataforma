package repository.persona

import models.Persona
import repository.ICrudRepository

interface IPersonaRepository: ICrudRepository<Long, Persona> {
}