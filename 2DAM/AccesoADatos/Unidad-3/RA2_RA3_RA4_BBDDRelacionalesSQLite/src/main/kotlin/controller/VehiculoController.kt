package controller

import cache.VehiculoCacheController
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

import errors.VehiculosErrors
import mappers.VehiculoDtoToVehiculo
import mappers.VehiculoToVehiculoDto
import models.Vehiculo
import repositories.CrudRepositoryVehiculoImplement
import repositories.ICrudRepositoryVehiculo
import storage.CsvStorage
import storage.Storage



class VehiculoController(
    val storageCarCsv : Storage = CsvStorage(),
    val repositorioVehiculos  : ICrudRepositoryVehiculo =  CrudRepositoryVehiculoImplement()
) {
    fun getAllVehiculos(): List<database.VehiculoDto> {
        var vehiculosDto = repositorioVehiculos.findAll()
        vehiculosDto.forEach{VehiculoCacheController.cache.put(it.id, it)}
        return vehiculosDto
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
                var carUpdate = repositorioVehiculos.findByUuid(vehiculo.uuid)
                if (carUpdate != null) {
                    VehiculoCacheController.cache.invalidate(vehiculo.id)
                    VehiculoCacheController.cache.put(carUpdate.id,carUpdate)
                    result = Ok(carUpdate.VehiculoDtoToVehiculo())
                }
            }
            result = Err(VehiculosErrors.VehiculoNotSaved("No hemos podido almacenar en la base de datos la inserción/actualización del vehiculo"))
        }
        return result
    }
    fun deleteVehiculo(carId: Long):Result<Boolean, VehiculosErrors>  {
        if(repositorioVehiculos.dropById(carId)){
            VehiculoCacheController.cache.invalidate(carId)
            return Ok(true)
        }else{
            return Err(VehiculosErrors.VehiculoNotFound("Error al borrar el vehiculo"))
        }
    }
    fun saveAllVehiculos(vehiculoList: List<Vehiculo>) {
        vehiculoList.forEach { saveVehiculo(it) }
    }

    fun getVehiculoById(id:Long):Result<Vehiculo, VehiculosErrors> {
        var vehiculo = VehiculoCacheController.cache.get(id)
        if(vehiculo == null){
            vehiculo = repositorioVehiculos.findById(id)
            if (vehiculo != null) {
                return Ok(vehiculo.VehiculoDtoToVehiculo())
            }
            return Err(VehiculosErrors.VehiculoNotFound("Vehiculo no encontrado con id: $id"))
        }
        return Ok(vehiculo.VehiculoDtoToVehiculo())
    }

    fun getVehiculoByUuid(uuid:String):Result<Vehiculo, VehiculosErrors> {
        var vehiculo : database.VehiculoDto? = repositorioVehiculos.findByUuid(uuid)
        if (vehiculo != null) {
            return Ok(vehiculo.VehiculoDtoToVehiculo())
        }else{return Err(VehiculosErrors.VehiculoNotFound("Vehiculo no encontrado con UUID: $uuid"))}
    }


    fun getVehiculosFromCSV(url : String, deleteBefore : Boolean) {
        if (deleteBefore == true) {
            dropAllVehiculos()
        }
        println("Leemos datos de el csv")
        saveAllVehiculos(storageCarCsv.readVehiculoDto(url).map { it.VehiculoDtoToVehiculo() })

    }
}
