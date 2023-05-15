package repository.vehiculo

import models.Persona
import models.Vehiculo
import repository.ICrudRepostory

interface IVehiculoRepository: ICrudRepostory<String, Vehiculo> {
    fun findAllByPersonaId(personaId: String): List<Vehiculo>

}