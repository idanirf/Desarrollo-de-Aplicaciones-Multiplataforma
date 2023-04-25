package repositories

import models.VehiculoDto
import services.SqlDelightClient
import java.time.LocalDate

class CrudRepositoryVehiculoImplement: ICrudRepositoryVehiculo {
    val db = SqlDelightClient.queries

    override fun findAll(): List<database.VehiculoDto> {
        return db.findAll().executeAsList()
    }

    override fun exixstsById(id: Long): Boolean {
        var vehiculo = db.findById(id).executeAsOneOrNull()
        return vehiculo != null
    }

    override fun create(vehiculo: database.VehiculoDto): Int {
        db.create(vehiculo.uuid, vehiculo.marca, vehiculo.modelo, vehiculo.matricula, vehiculo.fechaMatriculacion, vehiculo.tipoMotor, vehiculo.createdAt
            , vehiculo.updatedAt, vehiculo.deleted)
        return if (db.findByUuid(vehiculo.uuid).executeAsOneOrNull() == null) {
            0
        } else {
            1
        }
    }


    override fun findById(id: Long): database.VehiculoDto? {
        var vehiculo = db.findById(id).executeAsOneOrNull()
        return vehiculo

    }

    override fun dropById(id: Long): Boolean {
        db.dropById(id)
        return findById(id) == null
    }


    override fun findByUuid(uuid: String): database.VehiculoDto? {
        return db.findByUuid(uuid).executeAsOneOrNull()
    }


    override fun updateByUuid(vehiculo: database.VehiculoDto): Boolean {
        var act = LocalDate.now().toString()
        var vehiculoAActualizar = vehiculo.copy(updatedAt = act)
        db.updateById(vehiculo.uuid, vehiculo.marca, vehiculo.modelo, vehiculo.matricula, vehiculo.fechaMatriculacion, vehiculo.tipoMotor, vehiculo.createdAt
            , act, vehiculo.deleted, vehiculo.id)
        var vehiculoSave = db.findById(vehiculo.id).executeAsOneOrNull()
        return vehiculoSave?.equals(vehiculoAActualizar) == true
    }
}
