package controller

import cache.VehiculoCacheController
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import errors.VehiculosErrors
import mappers.VehiculoDtoToVehiculo
import mappers.VehiculoToVehiculoDto
import models.Vehiculo
import repositories.CrudRepositoryVehiculoImplement
import storage.CsvStorage
import storage.Storage
import com.github.michaelbull.result.*
import models.VehiculoDto
import validators.ValidatorVehiculo


class VehiculoController(
    val storageCarCsv : Storage = CsvStorage(),
    val repositorioVehiculos  :CrudRepositoryVehiculoImplement =  CrudRepositoryVehiculoImplement()
) {
    fun getAllVehiculos(): List<VehiculoDto> {
        var vehiculos = repositorioVehiculos.findAll()
        vehiculos.forEach { VehiculoCacheController.cache.put(it.id, it) }
        return vehiculos
    }
    fun dropAllVehiculos(){
        repositorioVehiculos.findAll().stream().forEach {repositorioVehiculos.dropById(it.id)}
        VehiculoCacheController.cache.invalidateAll()
    }

    fun saveVehiculo(vehiculo: Vehiculo):Result<Vehiculo, VehiculosErrors>  {
        var result :Result<Vehiculo, VehiculosErrors>
        if (repositorioVehiculos.exixstsById(vehiculo.id)){
            println("El vehiculo introducido existe en la base de datos, actualizando datos ...")
            var ok = repositorioVehiculos.updateByUuid(vehiculo.VehiculoToVehiculoDto())
            if(ok){
                result = Ok(vehiculo)
                VehiculoCacheController.cache.put(vehiculo.id,vehiculo.VehiculoToVehiculoDto())
            }else{
                result = Err(VehiculosErrors.VehiculoNotSaved("Error al almacenar en la base de datos elvehiculo"))
            }
        }else{
            var res= repositorioVehiculos.create(vehiculo.VehiculoToVehiculoDto())
            if(res>0){
                var vehiculoActualizado = repositorioVehiculos.findByUuid(vehiculo.uuid)
                if (vehiculoActualizado != null) {
                    VehiculoCacheController.cache.invalidate(vehiculo.id)
                    VehiculoCacheController.cache.put(vehiculoActualizado.id,vehiculoActualizado)
                    result = Ok(vehiculoActualizado.VehiculoDtoToVehiculo())
                }
            }
            result = Err(VehiculosErrors.VehiculoNotSaved("No hemos podido almacenar en la base de datos la inserción/actualización del vehiculo"))
        }
        return result
    }

    fun deleteVehiculo(vehiculoId: Long):Result<Boolean, VehiculosErrors>  {
        if(repositorioVehiculos.dropById(vehiculoId)){
            VehiculoCacheController.cache.invalidate(vehiculoId)
            return Ok(true)
        }else{
            return Err(VehiculosErrors.VehiculoNotFound("No se ha podido eliminar el vehiculo"))
        }
    }

    fun saveAllCars(listaVehiculo: List<Vehiculo>) {
        listaVehiculo.forEach { saveVehiculo(it) }
    }

    fun saveAllCarsToJson(url: String, cars : List<Vehiculo>):Result<Boolean,VehiculosErrors> {
        cars.forEach { it.ValidatorVehiculo().onFailure { return Err(VehiculosErrors.VehiculoNotValid(it.message)) }}
        val vehiculoDto =cars.map {it.VehiculoToVehiculoDto()}
        return Ok(true)
    }

    fun getVehiculoById(id:Long):Result<Vehiculo, VehiculosErrors> {
        var vehiculo = VehiculoCacheController.cache.get(id)
        if(vehiculo == null){
            vehiculo = repositorioVehiculos.findById(id)
            if (vehiculo != null) {
                return Ok(vehiculo.VehiculoDtoToVehiculo())
            }
            return Err(VehiculosErrors.VehiculoNotFound("car no encontrado con id: $id"))
        }
        return Ok(vehiculo.VehiculoDtoToVehiculo())
    }

    fun getVehiculoByUuid(uuid:String):Result<Vehiculo, VehiculosErrors> {
        var vehiculo : VehiculoDto? = repositorioVehiculos.findByUuid(uuid)
        if (vehiculo != null) {
            return Ok(vehiculo.VehiculoDtoToVehiculo())
        }else{return Err(VehiculosErrors.VehiculoNotFound("car no encaontrado con uuid: $uuid"))}
    }

    fun initVehiculoFormCsv(url : String, deleteBefore : Boolean) {
        if (deleteBefore == true) {
            dropAllVehiculos()
        }
        saveAllCars(storageCarCsv.readVehiculoDto(url).map { it.VehiculoDtoToVehiculo() }.toList())

    }
}
