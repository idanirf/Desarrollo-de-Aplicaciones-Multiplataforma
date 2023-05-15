package repository.persona

import models.Persona
import models.Vehiculo
import repository.ICrudRepostory

interface IPersonaRepository: ICrudRepostory<String, Persona> {
}