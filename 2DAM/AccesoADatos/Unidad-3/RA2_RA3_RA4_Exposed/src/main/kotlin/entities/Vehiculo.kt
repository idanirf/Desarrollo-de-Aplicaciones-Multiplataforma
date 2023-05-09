package entities

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.ReferenceOption
import java.util.UUID

object VehiculosTable: UUIDTable(){
    val marca = varchar("marca", 50)
    val modelo = varchar("modelo", 50)
    val matricula = varchar("matricula", 50)
    val personaId2 = long("persona_id").references(PersonaTable.id, onDelete = ReferenceOption.SET_NULL, onUpdate = ReferenceOption.CASCADE).nullable()


    }


class VehiculoDao(id: EntityID<UUID>): UUIDEntity(id){
    companion object : UUIDEntityClass<VehiculoDao>(VehiculosTable)

    var marca by VehiculosTable.marca
    var modelo by VehiculosTable.modelo
    var matricula by VehiculosTable.matricula
    var persona by VehiculosTable.personaId2
}