package repositories

import models.Vehiculo
import models.VehiculoDto
import services.DBaseController
import java.sql.Statement
import java.util.*

class CrudRepositoryVehiculoImplement: ICrudRepositoryVehiculo {
    override fun findAll(): List<VehiculoDto> {
        var sql = "SELECT * FROM vehiculos"
        val list = ArrayList<VehiculoDto>()
        var rs = DBaseController.db.prepareStatement(sql).use { r ->
            var resul = r.executeQuery()
            while (resul.next()) {
                var vehiculoDto = VehiculoDto(
                    id = resul.getLong(1),
                    uuid = resul.getString(2),
                    marca = resul.getString(3),
                    modelo = resul.getString(4),
                    matricula = resul.getString(5),
                    fechaMatriculacion = resul.getString(6),
                     tipoMotor =  resul.getString(7),
                    createdAt = resul.getString(8),
                    updatedAt = resul.getString(9),
                    deleted = resul.getString(10)
                )
                list.add(vehiculoDto)
            }
        }
        return list
    }

    override fun exixstsById(id: Long): Boolean {
        var vehiculo = findById(id)
        if (vehiculo == null) {
            return false
        }
        return true
    }

    override fun create(vehiculo: VehiculoDto): Int {
        var res =0
        var id: Long = 0
        var sql =
            """INSERT INTO vehiculos (uuid, marca, modelo, matricula, fechaMatriculacion, tipoMotor, createdAt, updatedAt, deleted) VALUES (?,?,?,?,?,?,?,?,?)"""
        var rs = DBaseController.db.prepareStatement(sql,   Statement.RETURN_GENERATED_KEYS).use { r ->
            r.setString(1, vehiculo.uuid)
            r.setString(2, vehiculo.marca)
            r.setString(3, vehiculo.modelo)
            r.setString(4, vehiculo.matricula)
            r.setString(5, vehiculo.fechaMatriculacion)
            r.setString(6, vehiculo.tipoMotor)
            r.setString(7, vehiculo.createdAt)
            r.setString(8, vehiculo.updatedAt)
            r.setString(9, vehiculo.deleted)
            res = r.executeUpdate()
            var key = r.generatedKeys
            if (key.next()) {
                id = key.getLong(1)
            }
            return res
        }
    }

    override fun findById(id: Long): VehiculoDto? {
        var sql = "SELECT * FROM vehiculos WHERE id =?"
        val list = ArrayList<VehiculoDto>()
        var rs = DBaseController.db.prepareStatement(sql).use { s ->
            s.setInt(1, id.toInt())
            var res = s.executeQuery()
            while (res.next()) {
                list.add(VehiculoDto(
                    id = res.getLong(1),
                    uuid = res.getString(2),
                    marca = res.getString(3),
                    modelo = res.getString(4),
                    matricula = res.getString(5),
                    fechaMatriculacion = res.getString(6),
                    tipoMotor =  res.getString(7),
                    createdAt = res.getString(8),
                    updatedAt = res.getString(9),
                    deleted = res.getString(10)))
            }
        }
        return list.firstOrNull()
    }

    override fun dropById(id: Long): Boolean {
        var sql = "DELETE FROM vehiculos WHERE id = ?"
        var res = 0
        var rs = DBaseController.db.prepareStatement(sql).use { r ->
            r.setInt(1, id.toInt())
            res = r.executeUpdate()
        }
        return res > 0
    }

    override fun findByUuid(uuid: String): VehiculoDto? {
        var sql = "SELECT * FROM vehiculos WHERE uuid =?"
        val list = ArrayList<VehiculoDto>()
        var rs = DBaseController.db.prepareStatement(sql).use { s ->
            s.setString(1, uuid)
            var res = s.executeQuery()
            while (res.next()) {
                list.add(VehiculoDto(
                    id = res.getLong(1),
                    uuid = res.getString(2),
                    marca = res.getString(3),
                    modelo = res.getString(4),
                    matricula = res.getString(5),
                    fechaMatriculacion = res.getString(6),
                    tipoMotor =  res.getString(7),
                    createdAt = res.getString(8),
                    updatedAt = res.getString(9),
                    deleted = res.getString(10)))
            }
        }
        return list.firstOrNull()
    }


    override fun updateByUuid(vehiculo: VehiculoDto): Boolean {
        var sql = "UPDATE vehiculos SET marca = ?, modelo = ?, matricula = ?, fechaMatriculacion = ?, tipoMotor = ?, updatedAt = ? WHERE uuid = ?"
        var res = 0
        var rs = DBaseController.db.prepareStatement(sql).use { r ->
            r.setString(1, vehiculo.marca)
            r.setString(2, vehiculo.modelo)
            r.setString(3, vehiculo.matricula)
            r.setString(4, vehiculo.fechaMatriculacion)
            r.setString(5, vehiculo.tipoMotor)
            r.setString(6, vehiculo.updatedAt)
            r.setString(7, vehiculo.uuid)
            res = r.executeUpdate()
        }
        return res > 0
    }

}