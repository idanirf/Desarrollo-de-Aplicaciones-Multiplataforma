package controller

import models.Persona
import models.Vehiculo
import repository.persona.IPersonaRepository
import repository.persona.PersonasRepository
import repository.vehiculo.IVehiculoRepository
import repository.vehiculo.VehiculoRepository

class ControllerVehiculos(
    val personas: IPersonaRepository,
    val vehiculos: IVehiculoRepository,
) {
    fun insertPersona(p: Persona){
        personas.create(p)
    }

    fun createVehiculo(p: Vehiculo) {
        if (p.uuidPersona != null){
            println("comprobamos que el id de la persona exixte")
            var personaCorrecta = personas.findById(p.uuidPersona!!)
            if (personaCorrecta == null){
                println("Vehiculo no creado")
            }else{
                vehiculos.create(p)
            }
        }else{
            vehiculos.create(p)
        }
    }

    fun createPersona(p: Persona){
        personas.create(p)
    }

    fun deleteVehiculos(uuid:String){
        vehiculos.delete(uuid)
    }

    fun findAllVehiculos(): List<Vehiculo>{
        return vehiculos.findAll()
    }

    fun findAllPersonas(): List<Persona>{
        return personas.findAll()
    }

    fun deleteAllVehiculos(){
        vehiculos.deleteAll()
    }

    fun deleteAllPersonas(){
        personas.deleteAll()
    }

    fun findByuuidPersona(uuid:String): Persona?{
        return personas.findById(uuid)
    }

    fun findByuuidVehiculo(uuid:String): Vehiculo?{
        return vehiculos.findById(uuid)
    }

    fun updateVehiculo(vehiculo:Vehiculo){
        if (vehiculo.uuidPersona!= null){
            var personaCorrecta = personas.findById(vehiculo.uuidPersona!!)
            if (personaCorrecta == null){
                println("Vehiculo no creado")
            }else{
                vehiculos.update(vehiculo)
            }
        } else{
            vehiculos.update(vehiculo)
        }

    }

    fun updatePersona(p: Persona){
        personas.update(p)
    }

    fun deletePersona(uuid:String){
        var listVehiculos = vehiculos.findAllByPersonaId(uuid)
        if (listVehiculos.size == 0){
            personas.delete(uuid)
        } else {
            println("No se puede borrar el vehiculo.")
        }
    }


}