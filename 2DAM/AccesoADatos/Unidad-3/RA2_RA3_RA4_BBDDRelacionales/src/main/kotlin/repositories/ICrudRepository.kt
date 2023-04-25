package repositories

import models.VehiculoDto

interface ICrudRepository<ID, T> {
    fun findAll(): List<VehiculoDto>
    fun exixstsById(id: ID): Boolean
    fun create(vehiculo: VehiculoDto): Int
    fun findById(id: ID): T?
    fun dropById(id: ID): Boolean
    fun findByUuid(uuid: String): T?
    fun updateByUuid(vehiculo: VehiculoDto): Boolean
}